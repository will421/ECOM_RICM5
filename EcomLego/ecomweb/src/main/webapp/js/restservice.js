/**
 * Angular REST service api for Lego site
 * 
 */
var restservice = angular.module('restservice', ['ngResource']);

restservice.factory('Rest', ['$resource', function ($resource) {
        return {
            PRODUIT: $resource('http://demo8894144.mockable.io', {}, {
                query: {method: 'GET'},
            }),
            POSTNEWSINGLEUSER: $resource('/resource/rest/users', {}, {
                post: {method: 'POST'}
            })
        };
    }]);