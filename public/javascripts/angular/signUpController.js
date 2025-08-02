app.controller('signUpController', function($http, $window) {

    var pageController = this;
    pageController.businessName = undefined;
    pageController.email = undefined;

    pageController.basicInfo = function() {
        console.log("Inside basic Info");
        pageController.validateEmail();
    };

    pageController.validateEmail = function (email) {
        $http({
            method: 'GET',
            url: '/users/email='+ email
        }).then(function successCallback(response) {
            if(response.status === 200) {
                pageController.emailExists = response.data.data;
                console.log("email  exist " + pageController.emailExists);
                if(!pageController.emailExists) {
                    if($window.location.hostname === 'localhost') {
                        $window.location.href =
                            "http://" + $window.location.host +"/register-business-user?businessName=" + pageController.businessName + "&email=" + pageController.email
                    } else {
                        $window.location.href =
                            "http://" + $window.location.hostname +"/register-business-user?businessName=" + pageController.businessName + "&email=" + pageController.email
                    }
                }
            }
        }, function errorCallback(response) {
            console.log("Request failed!!");
        });
    };

});