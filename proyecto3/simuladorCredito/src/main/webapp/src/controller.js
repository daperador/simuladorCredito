'use strict';

/* Controllers */
var module = angular.module('simuladorCredito.controllers', []);

module.controller('inicioCtrl', ['$scope', '$http', function($scope, $http) {
    $scope.datosFormulario={};
    $scope.registrarse=function(){
        $('#dlgRegistro').modal();
    };
    
    $scope.aceptar=function(){
        $http.post('./webresources/registro', $scope.datosFormulario, {}
                ).success(function(data, status, headers, config) {
                    alert("Los datos han sido guardados con Exito: "+data.id);
                    $('#dlgRegistro').modal('toggle');
                }).error(function(data, status, headers, config) {
                    alert('Error al consultar la información, por favor intente más tarde');
                });

    };
}]);