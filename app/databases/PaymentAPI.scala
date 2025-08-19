package databases

import datamodels._
import play.api.libs.json._
import javax.inject.{Inject, Singleton}
import play.api.db.Database
import java.sql.{Connection, PreparedStatement, ResultSet, Timestamp, Statement}
import java.time.LocalDateTime
import scala.util.{Try, Success, Failure}

trait PaymentAPI {
  // Payment Methods
  def createPaymentMethod(paymentMethod: PaymentMethod): Try[PaymentMethod]
  def getPaymentMethodsByBusiness(businessId: Long): Try[List[PaymentMethod]]
  def getPaymentMethodById(id: Long): Try[Option[PaymentMethod]]
  def deletePaymentMethod(id: Long): Try[Boolean]
  
  // Payment Intents
  def createPaymentIntent(paymentIntent: PaymentIntent): Try[PaymentIntent]
  def getPaymentIntentById(id: Long): Try[Option[PaymentIntent]]
  def getPaymentIntentByStripeId(stripePaymentIntentId: String): Try[Option[PaymentIntent]]
  def updatePaymentIntent(id: Long, paymentIntent: PaymentIntent): Try[PaymentIntent]
  def getPaymentIntentsByBusiness(businessId: Long): Try[List[PaymentIntent]]
  
  // Transactions
  def createTransaction(transaction: Transaction): Try[Transaction]
  def getTransactionById(id: Long): Try[Option[Transaction]]
  def getTransactionsByBusiness(businessId: Long): Try[List[Transaction]]
  def updateTransactionStatus(id: Long, status: TransactionStatus): Try[Boolean]
}

@Singleton
class PaymentAPIImpl @Inject()(db: Database) extends PaymentAPI {

  override def createPaymentMethod(paymentMethod: PaymentMethod): Try[PaymentMethod] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = """
          INSERT INTO payment_methods (business_id, stripe_payment_method_id, type, last4, brand, exp_month, exp_year, is_default, created_at, updated_at)
          VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())
        """
        val stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        stmt.setLong(1, paymentMethod.businessId)
        stmt.setString(2, paymentMethod.stripePaymentMethodId)
        stmt.setString(3, PaymentMethodType.toString(paymentMethod.methodType))
        stmt.setString(4, paymentMethod.last4.orNull)
        stmt.setString(5, paymentMethod.brand.orNull)
        stmt.setObject(6, paymentMethod.expMonth.map(Integer.valueOf).orNull)
        stmt.setObject(7, paymentMethod.expYear.map(Integer.valueOf).orNull)
        stmt.setBoolean(8, paymentMethod.isDefault)
        
        stmt.executeUpdate()
        val rs = stmt.getGeneratedKeys
        if (rs.next()) {
          val id = rs.getLong(1)
          paymentMethod.copy(id = Some(id))
        } else {
          throw new RuntimeException("Failed to create payment method")
        }
      }
    }
  }

  override def getPaymentMethodsByBusiness(businessId: Long): Try[List[PaymentMethod]] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = """
          SELECT id, business_id, stripe_payment_method_id, type, last4, brand, exp_month, exp_year, is_default, created_at, updated_at
          FROM payment_methods WHERE business_id = ? ORDER BY is_default DESC, created_at DESC
        """
        val stmt = connection.prepareStatement(sql)
        stmt.setLong(1, businessId)
        val rs = stmt.executeQuery()
        
        val methods = scala.collection.mutable.ListBuffer[PaymentMethod]()
        while (rs.next()) {
          methods += mapResultSetToPaymentMethod(rs)
        }
        methods.toList
      }
    }
  }

  override def getPaymentMethodById(id: Long): Try[Option[PaymentMethod]] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = """
          SELECT id, business_id, stripe_payment_method_id, type, last4, brand, exp_month, exp_year, is_default, created_at, updated_at
          FROM payment_methods WHERE id = ?
        """
        val stmt = connection.prepareStatement(sql)
        stmt.setLong(1, id)
        val rs = stmt.executeQuery()
        
        if (rs.next()) {
          Some(mapResultSetToPaymentMethod(rs))
        } else {
          None
        }
      }
    }
  }

  override def deletePaymentMethod(id: Long): Try[Boolean] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = "DELETE FROM payment_methods WHERE id = ?"
        val stmt = connection.prepareStatement(sql)
        stmt.setLong(1, id)
        stmt.executeUpdate() > 0
      }
    }
  }

  override def createPaymentIntent(paymentIntent: PaymentIntent): Try[PaymentIntent] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = """
          INSERT INTO payment_intents (business_id, stripe_payment_intent_id, amount, currency, status, description, customer_email, customer_name, metadata, created_at, updated_at)
          VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())
        """
        val stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        stmt.setLong(1, paymentIntent.businessId)
        stmt.setString(2, paymentIntent.stripePaymentIntentId)
        stmt.setLong(3, paymentIntent.amount)
        stmt.setString(4, paymentIntent.currency)
        stmt.setString(5, PaymentIntentStatus.toString(paymentIntent.status))
        stmt.setString(6, paymentIntent.description.orNull)
        stmt.setString(7, paymentIntent.customerEmail.orNull)
        stmt.setString(8, paymentIntent.customerName.orNull)
        stmt.setString(9, paymentIntent.metadata.map(Json.stringify).orNull)
        
        stmt.executeUpdate()
        val rs = stmt.getGeneratedKeys
        if (rs.next()) {
          val id = rs.getLong(1)
          paymentIntent.copy(id = Some(id))
        } else {
          throw new RuntimeException("Failed to create payment intent")
        }
      }
    }
  }

  override def getPaymentIntentById(id: Long): Try[Option[PaymentIntent]] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = """
          SELECT id, business_id, stripe_payment_intent_id, amount, currency, status, description, customer_email, customer_name, metadata, created_at, updated_at
          FROM payment_intents WHERE id = ?
        """
        val stmt = connection.prepareStatement(sql)
        stmt.setLong(1, id)
        val rs = stmt.executeQuery()
        
        if (rs.next()) {
          Some(mapResultSetToPaymentIntent(rs))
        } else {
          None
        }
      }
    }
  }

  override def getPaymentIntentByStripeId(stripePaymentIntentId: String): Try[Option[PaymentIntent]] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = """
          SELECT id, business_id, stripe_payment_intent_id, amount, currency, status, description, customer_email, customer_name, metadata, created_at, updated_at
          FROM payment_intents WHERE stripe_payment_intent_id = ?
        """
        val stmt = connection.prepareStatement(sql)
        stmt.setString(1, stripePaymentIntentId)
        val rs = stmt.executeQuery()
        
        if (rs.next()) {
          Some(mapResultSetToPaymentIntent(rs))
        } else {
          None
        }
      }
    }
  }

  override def updatePaymentIntent(id: Long, paymentIntent: PaymentIntent): Try[PaymentIntent] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = """
          UPDATE payment_intents 
          SET amount = ?, currency = ?, status = ?, description = ?, customer_email = ?, customer_name = ?, metadata = ?, updated_at = NOW()
          WHERE id = ?
        """
        val stmt = connection.prepareStatement(sql)
        stmt.setLong(1, paymentIntent.amount)
        stmt.setString(2, paymentIntent.currency)
        stmt.setString(3, PaymentIntentStatus.toString(paymentIntent.status))
        stmt.setString(4, paymentIntent.description.orNull)
        stmt.setString(5, paymentIntent.customerEmail.orNull)
        stmt.setString(6, paymentIntent.customerName.orNull)
        stmt.setString(7, paymentIntent.metadata.map(Json.stringify).orNull)
        stmt.setLong(8, id)
        
        val updated = stmt.executeUpdate()
        if (updated > 0) {
          paymentIntent.copy(id = Some(id))
        } else {
          throw new RuntimeException("Failed to update payment intent")
        }
      }
    }
  }

  override def getPaymentIntentsByBusiness(businessId: Long): Try[List[PaymentIntent]] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = """
          SELECT id, business_id, stripe_payment_intent_id, amount, currency, status, description, customer_email, customer_name, metadata, created_at, updated_at
          FROM payment_intents WHERE business_id = ? ORDER BY created_at DESC
        """
        val stmt = connection.prepareStatement(sql)
        stmt.setLong(1, businessId)
        val rs = stmt.executeQuery()
        
        val intents = scala.collection.mutable.ListBuffer[PaymentIntent]()
        while (rs.next()) {
          intents += mapResultSetToPaymentIntent(rs)
        }
        intents.toList
      }
    }
  }

  override def createTransaction(transaction: Transaction): Try[Transaction] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = """
          INSERT INTO transactions (business_id, payment_intent_id, stripe_charge_id, amount, fee, net_amount, currency, status, description, customer_email, customer_name, receipt_url, created_at)
          VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())
        """
        val stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        stmt.setLong(1, transaction.businessId)
        stmt.setObject(2, transaction.paymentIntentId.map(java.lang.Long.valueOf).orNull)
        stmt.setString(3, transaction.stripeChargeId.orNull)
        stmt.setLong(4, transaction.amount)
        stmt.setObject(5, transaction.fee.map(java.lang.Long.valueOf).orNull)
        stmt.setObject(6, transaction.netAmount.map(java.lang.Long.valueOf).orNull)
        stmt.setString(7, transaction.currency)
        stmt.setString(8, TransactionStatus.toString(transaction.status))
        stmt.setString(9, transaction.description.orNull)
        stmt.setString(10, transaction.customerEmail.orNull)
        stmt.setString(11, transaction.customerName.orNull)
        stmt.setString(12, transaction.receiptUrl.orNull)
        
        stmt.executeUpdate()
        val rs = stmt.getGeneratedKeys
        if (rs.next()) {
          val id = rs.getLong(1)
          transaction.copy(id = Some(id))
        } else {
          throw new RuntimeException("Failed to create transaction")
        }
      }
    }
  }

  override def getTransactionById(id: Long): Try[Option[Transaction]] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = """
          SELECT id, business_id, payment_intent_id, stripe_charge_id, amount, fee, net_amount, currency, status, description, customer_email, customer_name, receipt_url, created_at
          FROM transactions WHERE id = ?
        """
        val stmt = connection.prepareStatement(sql)
        stmt.setLong(1, id)
        val rs = stmt.executeQuery()
        
        if (rs.next()) {
          Some(mapResultSetToTransaction(rs))
        } else {
          None
        }
      }
    }
  }

  override def getTransactionsByBusiness(businessId: Long): Try[List[Transaction]] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = """
          SELECT id, business_id, payment_intent_id, stripe_charge_id, amount, fee, net_amount, currency, status, description, customer_email, customer_name, receipt_url, created_at
          FROM transactions WHERE business_id = ? ORDER BY created_at DESC
        """
        val stmt = connection.prepareStatement(sql)
        stmt.setLong(1, businessId)
        val rs = stmt.executeQuery()
        
        val transactions = scala.collection.mutable.ListBuffer[Transaction]()
        while (rs.next()) {
          transactions += mapResultSetToTransaction(rs)
        }
        transactions.toList
      }
    }
  }

  override def updateTransactionStatus(id: Long, status: TransactionStatus): Try[Boolean] = {
    Try {
      db.withConnection { implicit connection =>
        val sql = "UPDATE transactions SET status = ? WHERE id = ?"
        val stmt = connection.prepareStatement(sql)
        stmt.setString(1, TransactionStatus.toString(status))
        stmt.setLong(2, id)
        stmt.executeUpdate() > 0
      }
    }
  }

  // Helper methods to map ResultSet to domain objects
  private def mapResultSetToPaymentMethod(rs: ResultSet): PaymentMethod = {
    PaymentMethod(
      id = Some(rs.getLong("id")),
      businessId = rs.getLong("business_id"),
      stripePaymentMethodId = rs.getString("stripe_payment_method_id"),
      methodType = PaymentMethodType.fromString(rs.getString("type")),
      last4 = Option(rs.getString("last4")),
      brand = Option(rs.getString("brand")),
      expMonth = Option(rs.getInt("exp_month")).filter(_ != 0),
      expYear = Option(rs.getInt("exp_year")).filter(_ != 0),
      isDefault = rs.getBoolean("is_default"),
      createdAt = Option(rs.getTimestamp("created_at").toLocalDateTime),
      updatedAt = Option(rs.getTimestamp("updated_at").toLocalDateTime)
    )
  }

  private def mapResultSetToPaymentIntent(rs: ResultSet): PaymentIntent = {
    val metadataStr = rs.getString("metadata")
    val metadata = if (metadataStr != null) Some(Json.parse(metadataStr)) else None
    
    PaymentIntent(
      id = Some(rs.getLong("id")),
      businessId = rs.getLong("business_id"),
      stripePaymentIntentId = rs.getString("stripe_payment_intent_id"),
      amount = rs.getLong("amount"),
      currency = rs.getString("currency"),
      status = PaymentIntentStatus.fromString(rs.getString("status")),
      description = Option(rs.getString("description")),
      customerEmail = Option(rs.getString("customer_email")),
      customerName = Option(rs.getString("customer_name")),
      metadata = metadata,
      createdAt = Option(rs.getTimestamp("created_at").toLocalDateTime),
      updatedAt = Option(rs.getTimestamp("updated_at").toLocalDateTime)
    )
  }

  private def mapResultSetToTransaction(rs: ResultSet): Transaction = {
    Transaction(
      id = Some(rs.getLong("id")),
      businessId = rs.getLong("business_id"),
      paymentIntentId = Option(rs.getLong("payment_intent_id")).filter(_ != 0),
      stripeChargeId = Option(rs.getString("stripe_charge_id")),
      amount = rs.getLong("amount"),
      fee = Option(rs.getLong("fee")).filter(_ != 0),
      netAmount = Option(rs.getLong("net_amount")).filter(_ != 0),
      currency = rs.getString("currency"),
      status = TransactionStatus.fromString(rs.getString("status")),
      description = Option(rs.getString("description")),
      customerEmail = Option(rs.getString("customer_email")),
      customerName = Option(rs.getString("customer_name")),
      receiptUrl = Option(rs.getString("receipt_url")),
      createdAt = Option(rs.getTimestamp("created_at").toLocalDateTime)
    )
  }
}
