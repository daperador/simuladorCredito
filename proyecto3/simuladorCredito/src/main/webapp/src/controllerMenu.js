'use strict';

/* Controllers */
var module = angular.module('simuladorCredito.controllers', []);

module.controller('menuCtrl', ['$scope', '$http', function($scope, $http) {
    $scope.lineas=[
      {id:1, nombre:'linea 1', tasa:9.23},
      {id:2, nombre:'linea 2', tasa: 8.12}];
    $scope.linea;
    $scope.administrador=1;
        
    $scope.crudLineas=function(){
        $http.get('webresources/linea/administrador/1', {})
        .success(function (data, status, headers, config) {
            $scope.lineas=data;
        }).error(function (data, status, headers, config) {
            alert('Error al consultar la información, por favor intente más tarde');
        });        
        $('#dlgLineas').modal();
    };
    
    $scope.editarLinea=function(lineaId){
        $http.get('webresources/linea/linea/'+lineaId, {})
        .success(function (data, status, headers, config) {
            $scope.linea=data;
            $scope.linea.actividad='actualizar';
            $('#dlgLinea').modal();
        }).error(function (data, status, headers, config) {
            alert('Error al consultar la información, por favor intente más tarde');
        });        
        
    };
    
    $scope.guardarLinea=function(){
        $scope.linea.administrador=$scope.administrador;
        if ($scope.linea.actividad == 'actualizar'){
            $http.put('webresources/linea/linea', $scope.linea)
            .success(function (data, status, headers, config) {
                $('#dlgLinea').modal('toggle');
            }).error(function (data, status, headers, config) {
                alert('Error al guardar la Línea, por favor intente más tarde');
            });
        }else{
            $http.post('webresources/linea/linea', $scope.linea)
            .success(function (data, status, headers, config) {
                $('#dlgLinea').modal('toggle');
            }).error(function (data, status, headers, config) {
                alert('Error al guardar la Línea, por favor intente más tarde');
            });
        }
    };
    
    $scope.nuevaLinea=function(){
        $scope.linea={id:null, nombre:'', tasa:0.0, actividad:'crear', administrador: $scope.administrador};
        $('#dlgLinea').modal();
    }
    
    $scope.listarPlanes=function(){
        $('#dlgPlanes').modal();
    };
    $scope.detallePlan=function(){
        $('#dlgPlan').modal();
    };
    
    
    
}]);