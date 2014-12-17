/**
 * Angular REST service api for Lego site
 * 
 */
var restservice = angular.module('restservice', ['ngResource']);

restservice.factory('Rest', ['$resource', function ($resource) {
        return {
            getAllProduits: $resource('resources/rest/products', {}, {
                query: {method: 'GET', isArray:true},
            }),
            
            POSTNEWSINGLEUSER: $resource('/resource/rest/users', {}, {
                post: {method: 'POST'}
            }),
        };
    }]);