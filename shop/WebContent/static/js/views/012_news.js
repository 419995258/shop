var aboutAppmodule = angular.module('newsApp', []);

aboutAppmodule.controller('newsController', function($scope, $http, $rootScope, $stateParams, $state,$sce) {
	////////////////////////////////////////////////////////判断当前页	
	function init() {		
		if ($state.current.name == 'news') {
			$('.qz_content > a').removeClass('active');
			$('#btn_news').addClass('active');
		};
		queryNews();
	};
	$scope.pageSize = 15;// 每页多少条
	$scope.maxSize = 15; //一次可现实多少分页标签，超过maxSize后则以...代替
    $scope.totalItems = 15; //总共多少条记录
    $scope.currentPage = 1; //当前页码
    $scope.pages = 1;//总页数
    $scope.newsShow ={};
    var queryParam = {};
    queryParam.pageSize = $scope.maxSize;
    queryParam.currentpage = $scope.currentPage;
    
    $scope.pageChanged = function () {
    	queryNews();
    };
	
	//查询新闻
    function queryNews() {
    	queryParam.currentpage = $scope.currentPage;
    	queryParam.pageSize = $scope.pageSize;
    	
        $http({
            url: '../otherManageC/showNews',
            method: 'POST',
            data: queryParam
        }).success(function (data) {
        	$scope.totalItems = data.total; //总共多少条记录
    	    $scope.currentPage = data.pageNum; //当前页码
    	    $scope.pages = data.pages;
    	    $scope.newsShow = data.rows;
        }).error(function (response) {
        });
      
    };
    
    $scope.newsContent = function(index) {
    	  console.log($scope.newsShow[index].id);
    	$state.go("newsContent", {id:$scope.newsShow[index].id}, { reload: true });
    };
	
	init();
});