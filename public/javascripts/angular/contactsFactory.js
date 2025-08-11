app.factory('ContactsFactory', function($http) {

    var basePath = '/businesses/contacts/';

    return {
        getAllContacts: function(businessId, success, error) {
            $http.get(basePath + businessId).then(success, error);
        },
        addContact: function(contact, success, error) {
            $http.post('/businesses/contacts', contact).then(success, error);
        },
        updateContact: function(contact, success, error) {
            $http.post('/businesses/contacts/update', contact).then(success, error);
        },
        deleteContactBy: function(contactId, businessId, success, error) {
            $http.delete('/businesses/' + businessId + '/contacts/' + contactId).then(success, error);
        }
    };
});
