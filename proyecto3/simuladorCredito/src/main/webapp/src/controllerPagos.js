'use strict';

/* Controllers */
var module = angular.module('simuladorCredito.controllers', []);

module.controller('planPagosCtrl', ['$scope', '$http', function ($scope, $http) {
        $scope.generando=false;
        $scope.datosFormulario={};
        
        $http.get('webresources/linea/administrador/1', {})
        .success(function (data, status, headers, config) {
            $scope.lineas=data;
        }).error(function (data, status, headers, config) {
            alert('Error al consultar la información, por favor intente más tarde');
        });
        
        $scope.registrar = function(){
            $scope.datosFormulario.fechaNacimiento=$('#fechaNacimiento').data().datepicker.viewDate.getTime();
            $http.post('webresources/planPago',$scope.datosFormulario,{})
            .success(function (data, status, headers, config) {
                $scope.lineas=data;
                $scope.actualizar=true;
                $scope.id=data.id;
            }).error(function (data, status, headers, config) {
                alert('Error al guardar la información, por favor intente más tarde');
            }); 
        };
        $scope.limpiar = function(){
            $scope.datosFormulario={};
        };
        $scope.actualizar = function(){
            
        };

    }]);