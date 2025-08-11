app.directive('editContactModal', [EditContactModalDirective]);

function EditContactModalDirective() {
    return {
        template: '<ng-include src="getTemplateUrl()"/>',
        scope: {
            businessId: '=',
            currentContact: '=',
            contacts: '='
        },
        bindToController: true,
        controller: EditContactModalController,
        controllerAs: 'editContactModalCtrl'
    };
}

// Controller is provided via directive, separate registration not needed

function EditContactModalController(ContactsFactory, $scope, templates) {
    var vm = this;

    $scope.getTemplateUrl = function() {
        return templates.editContactModal;
    };

    vm.updateContact = function () {
        var updated = angular.copy(vm.currentContact);
        updated.business_id = vm.businessId;
        ContactsFactory.updateContact(updated, function success () {
            // refresh via parent controller
            $scope.$parent.controller.getAllContacts(vm.businessId);
            alerts.autoCloseAlert('success-message', 'Contact updated!', '');
        }, function error () {
            alerts.autoCloseAlert('title-and-text', 'Oops something went wrong', 'Please try again!');
        });
    };
}
