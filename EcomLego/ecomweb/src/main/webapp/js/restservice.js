/**
 * Angular REST service api for Lego site
 * isArray is for reply
 */
var restservice = angular.module('restservice', ['ngResource']);

restservice.factory('Rest', ['$resource', function ($resource) {
        return {
            getAllProduits: $resource('resources/rest/products', {}, {
                query: {method: 'GET', isArray: true},
            }),
            getProduitsByTheme: $resource('resources/rest/products', {}, {
                query: {method: 'GET', isArray: true},
            }),
            createUser: $resource('resources/rest/users', {}, {
                query: {method: 'POST', isArray: false}
            }),
            testGet: $resource('http://demo8894144.mockable.io/', {}, {
                query: {method: 'GET'},
            }),
            login: $resource('resources/rest/users', {}, {
                query: {method: 'GET'},
            })
        };
    }]);