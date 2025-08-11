app.controller('contactsController', function(ContactsFactory, $rootScope) {
    var contactCtrl = this;
    contactCtrl.contacts = [];
    contactCtrl.currentContactId = null;
    contactCtrl.currentContact = null;
    contactCtrl.formData = {};

    contactCtrl.showNewItem = false;

    contactCtrl.newItem = function() {
        contactCtrl.formData = {};
        contactCtrl.showNewItem = true;
    };

    contactCtrl.cancelItem = function() {
        contactCtrl.showNewItem = false;
    };

    contactCtrl.saveItem = function(businessId) {
        createContact(businessId);
    };

    contactCtrl.getAllContacts = function(businessId) {
        loadContacts(businessId);
    };

    contactCtrl.setCurrentContact = function(contact) {
        $rootScope.$broadcast('currentContactSet', contact);
        contactCtrl.currentContact = contact;
        contactCtrl.currentContactId = contact.id;
    };

    contactCtrl.updateContact = function(businessId) {
        var updated = angular.copy(contactCtrl.currentContact);
        updated.business_id = businessId;
        ContactsFactory.updateContact(updated, function success() {
            refresh(businessId);
            alerts.autoCloseAlert('success-message', 'Contact updated!!', '');
        }, function error() {
            alerts.autoCloseAlert('title-and-text', 'Oops something went wrong', 'Please try again!');
        });
    };

    contactCtrl.removeContact = function(businessId, contactId) {
        deleteContact(businessId, contactId);
    };

    function loadContacts(businessId) {
        ContactsFactory.getAllContacts(businessId, function success(response) {
            contactCtrl.contacts = response.data.data;
        }, function error(response) {
            console.log(response.statusText);
        });
    }

    function refresh(businessId) {
        contactCtrl.showNewItem = false;
        loadContacts(businessId);
    }

    function clearFormData() {
        contactCtrl.formData = {};
    }

    function createContact(businessId) {
        var newContact = angular.copy(contactCtrl.formData);
        newContact.business_id = businessId;
        ContactsFactory.addContact(newContact, function success() {
            refresh(businessId);
            clearFormData();
            alerts.autoCloseAlert('success-message', 'New contact added!!', '');
        }, function error() {
            clearFormData();
            alerts.autoCloseAlert('success-message', 'Oops something went wrong', 'Please try again!');
        });
    }

    function deleteContact(businessId, contactId) {
        ContactsFactory.deleteContactBy(contactId, businessId, function success() {
            refresh(businessId);
            alerts.autoCloseAlert('success-message', 'Contact removed!!', '');
        }, function error() {
            alerts.autoCloseAlert('title-and-text', 'Oops something went wrong', 'Please try again!');
        });
    }
});
