var newsContentAppmodule = angular.module('newsContentApp', []);

newsContentAppmodule.controller('newsContentController', function($scope, $http, $rootScope, $stateParams, $state,$sce) {
	////////////////////////////////////////////////////////判断当前页	
	function init() {		
		if ($state.current.name == 'newsContent') {
			$('.qz_content > a').removeClass('active');
			$('#btn_news').addClass('active');
		};
		queryNewsById();
	};
	
////////////////////////////////////////////////////////测试数据
	$scope.newsC = {};
	var newsId = $stateParams.id;
	function queryNewsById() {
        $http({
            url: '../otherManageC/queryInfo',
            method: 'POST',
            data: newsId
        }).success(function (data) {
        	if (!verifyIsNull(data.content)) {
        		data.content = $sce.trustAsHtml(data.content);
        	}
        	
        	$scope.newsC = data;
        }).error(function (response) {
        });
    };
	
	init();
});