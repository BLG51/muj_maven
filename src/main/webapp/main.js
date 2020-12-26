$(document).ready(function(){
	$('.header').height($(window).height());
});

$(function() {
$(".navbar a").on('click', function(){
	$("html, body").animate({
		scrollTop:$('#' + $(this).data('value')).offset().top
	},1000)
});
});