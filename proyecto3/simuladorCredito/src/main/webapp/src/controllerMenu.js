'use strict';

/* Controllers */
var module = angular.module('simuladorCredito.controllers', []);

module.controller('menuCtrl', ['$scope', '$http', function($scope, $http) {
    
    $scope.crudLineas=function(){
        $('#dlgLineas').modal();
    };
    $scope.editarLinea=function(){
        $('#dlgLinea').modal();
    };    
    $scope.listarPlanes=function(){
        $('#dlgPlanes').modal();
    };
    $scope.detallePlan=function(){
        $('#dlgPlan').modal();
    };
    
}]);