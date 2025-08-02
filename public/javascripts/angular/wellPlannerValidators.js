wellPlannerValidators = {

   isValidAlphanumericWithSpaces: function (value) {
     return /^[A-Za-z0-9 ]+$/.test( value );
   },
   isNotDigitsOnly: function (value) {
     return !(/^\d+$/.test( value ));
   },
   isOurPasswordOnly: function (password) {
       return (!!(password.match(/[a-z]/g) && password.match(/[A-Z]/g) && password.match(/[0-9]/g) && password.match(/[^a-zA-Z\d]/g) && password.length >= 8))
   }
};