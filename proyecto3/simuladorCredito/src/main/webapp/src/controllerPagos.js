'use strict';

/* Controllers */
var module = angular.module('simuladorCredito.controllers', []);

module.controller('planPagosCtrl', ['$scope', '$http', function($scope, $http) {
    $scope.limpiar = function(){
        $scope.datosPlanPagos.cedula = "";
        $scope.datosPlanPagos.fechaNacimiento = "";
        $scope.datosPlanPagos.valor = "";
        $scope.datosPlanPagos.plazo = "";
    };
    
    
}]);