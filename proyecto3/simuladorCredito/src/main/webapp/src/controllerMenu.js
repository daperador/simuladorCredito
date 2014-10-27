'use strict';

/* Controllers */
var module = angular.module('simuladorCredito.controllers', []);

module.controller('menuCtrl', ['$scope', '$http', function($scope, $http) {
    $scope.lineas=[];
    $scope.planes=[];
    $scope.linea;
    $scope.plan;
    $scope.administrador=1;
    $http.get('webresources/login/administrador/', {})
        .success(function (data, status, headers, config) {
            $scope.administrador=data;
        }).error(function (data, status, headers, config) {
            alert('Error al consultar la información, por favor intente más tarde');
        }); 
        
    $scope.crudLineas=function(){
        $scope.cargarLineas();
        $('#dlgLineas').modal();
    };
    
    
    $scope.cargarLineas=function(){
        $http.get('webresources/linea/administrador/'+$scope.administrador, {})
        .success(function (data, status, headers, config) {
            $scope.lineas=data;
        }).error(function (data, status, headers, config) {
            alert('Error al consultar la información, por favor intente más tarde');
        });        
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
                $scope.cargarLineas();
            }).error(function (data, status, headers, config) {
                alert('Error al guardar la Línea, por favor intente más tarde');
            });
        }else{
            $http.post('webresources/linea/linea', $scope.linea)
            .success(function (data, status, headers, config) {
                $('#dlgLinea').modal('toggle');
                $scope.cargarLineas();
            }).error(function (data, status, headers, config) {
                alert('Error al guardar la Línea, por favor intente más tarde');
            });
        }
        
    };
    
    $scope.nuevaLinea=function(){
        $scope.linea={id:null, nombre:'', tasa:0.0, actividad:'crear', administrador: $scope.administrador};
        $('#dlgLinea').modal();
    };
    
    $scope.eliminarLinea=function(lineaId){
        $http.delete('webresources/linea/linea/'+lineaId, {})
        .success(function (data, status, headers, config) {
            $scope.cargarLineas();
        }).error(function (data, status, headers, config) {
            alert('Error al consultar la información, por favor intente más tarde');
        });        
    };
    
    $scope.cargarPlanes=function(){
        $http.get('webresources/planPago/planesPago', {})
        .success(function (data, status, headers, config) {
            $scope.planes=data;
        }).error(function (data, status, headers, config) {
            alert('Error al consultar la información, por favor intente más tarde');
        });        
    };
    
    $scope.listarPlanes=function(){
        $scope.cargarPlanes();
        $('#dlgPlanes').modal();
    };
    
    $scope.detallePlan=function(planId){
        $http.get('webresources/planPago/planPago/'+planId, {})
        .success(function (data, status, headers, config) {
            $scope.plan=data;
            $('#dlgPlan').modal();
        }).error(function (data, status, headers, config) {
            alert('Error al consultar la información, por favor intente más tarde');
        });        
        
    };
    
    
    
}]);

