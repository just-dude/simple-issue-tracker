$(document).ready(function($){    
    var locale = utils.getShortLocaleOfPage();
    console.log(locale);  
    $("#openLoginWindowReference").authentication({locale: locale});
});


