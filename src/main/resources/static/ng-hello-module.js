angular
.module('app')
.controller('HelloController', ['$rootScope', '$http', '$log', function($rootScope, $http, $log) {

    var $ctrl = this;
    $log.info("Hello Controller is active.");

    $ctrl.getData = function() {
        $http.get("data")
        .then(function(response) {
            $ctrl.data = response.data;
        }).catch(function(e) { $log.error("getData FAILED"+e); })
    }

    $ctrl.getData();

}])
;