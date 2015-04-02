//VER:1.6 and AUTO-GENERATED on DATE:2014/08/06
(function () {
    var _params = [], i, _sc_params = [], _tmp_item, products;
    var empty = function (data) {
        if (typeof data === 'number' || typeof data === 'boolean') {
            return false;
        }
        if (typeof data === 'undefined' || data === null) {
            return true;
        }
        if (data.length !== undefined) {
            return data.length === 0;
        }
        var count = 0, j;
        for (j in data) {
            if (data.hasOwnProperty(j)) {
                count = count + 1;
            }
        }
        return count === 0;
    };
    function checkMobileSite(){
        return (window.location.hostname).indexOf("m.snapdeal.com") !== -1;
    }
    if (typeof _atm_params === undefined || _atm_params === null) {
        return false;
    }
    function trim(str){
        return str.replace(/^\s+|\s+$/g,'');
    }
    //Mapping
    if(typeof(purchasedItemsPOGs) === "object")
        products = purchasedItemsPOGs ? purchasedItemsPOGs : null;
    //Product ids for thankyou page
	if(_atm_params.f === "p" && _atm_params.id === "" && typeof(products) === "object" && products.length > 0){
	   _atm_params.id = products;
	}
    /**
    * Override category and subcategory parameters
    */
    if('querySelectorAll' in document){
        if(checkMobileSite()){
            var mobCat = document.querySelectorAll(".category-heading h1"),
                mobCatLen = mobCat.length,
                cnText,
                mobSubmenu;
                if(mobCatLen > 0){
                    _atm_params.scn = mobCat[0].innerText || mobCat[0].textContent;
                    if(document.querySelectorAll("#catbreadcrumb .active").length > 0){
                        mobSubmenu = document.querySelectorAll("#multilabelsubmenu a")[0];
                        if(mobSubmenu){
                            cnText = mobSubmenu.textContent || mobSubmenu.innerText;
                           _atm_params.cn = cnText.split("(").length > 1 ? trim(cnText.split("(")[0].replace("View All","")): trim(cnText.replace("View All",""));
                        }
                    }else{
                        _atm_params.cn = "Home";
                    }
                }
        }else{
        var bcItems = document.querySelectorAll("#breadCrumbWrapper .containerBreadcrumb span"),
            bcItemLen = bcItems.length,
            catStr = "",
            subcatStr = "";
        if(bcItemLen === 1){
            catStr = bcItems[0].innerText || bcItems[0].textContent;
            _atm_params.cn = catStr;
        }else if(bcItemLen > 1 && _atm_params.f !== "c"){
            // Not a product page
            catStr = bcItems[bcItemLen-2].innerText || bcItems[bcItemLen-2].textContent;
            subcatStr = bcItems[bcItemLen-1].innerText || bcItems[bcItemLen-1].textContent;
            if(typeof(catStr) === "string")
                _atm_params.cn = catStr;
            if(typeof(subcatStr) === "string")
                _atm_params.scn = subcatStr;
        }else if(bcItemLen > 2 && _atm_params.f === "c"){
            // Product page
            catStr = bcItems[bcItemLen-3].innerText || bcItems[bcItemLen-3].textContent;
            subcatStr = bcItems[bcItemLen-2].innerText || bcItems[bcItemLen-2].textContent;
            if(typeof(catStr) === "string")
                _atm_params.cn = catStr;
            if(typeof(subcatStr) === "string")
                _atm_params.scn = subcatStr;
        }
    }
    }

    for (i in _atm_params) {
        if (_atm_params.hasOwnProperty(i) && !empty(_atm_params[i])) {
            _tmp_item = encodeURIComponent(i) + "=" + encodeURIComponent(_atm_params[i]);
            _params.push(_tmp_item);
            if(i === "f"){
                _sc_params.push(encodeURIComponent(i) + "=" + encodeURIComponent("s"));
            }else{
                _sc_params.push(_tmp_item);
            }
        }
    }

    var _src_urls = [];
    var _query_string = _params.join('&');

    // data pixel for collecting both dco and site visitor data 
    var data_source = '//datacdn.atomex.net/data/2144/1x1.gif?client_id=' + _atm_client_id + '&' + _query_string;

    _src_urls.push(['image', data_source]);

    //sync pixel
    if(_atm_params.f != "v"){
        _src_urls.push(['script', '//sync.atomex.net/handlers?dsc=1&rh=rh0&atom_sc']);
    }
   
    //Custom code for clicks
    var _sc_query_string = _sc_params.join('&');
    var moreSeller;
    var sc_data_source = '//datacdn.atomex.net/data/2144/1x1.gif?client_id=' + _atm_client_id + '&' + _sc_query_string;
    if(checkMobileSite()){
    _src_urls.push(['click', {'tar':document.getElementById("buylink"), 'pxl': sc_data_source}]);
        if('querySelectorAll' in document){
            moreSeller = document.querySelectorAll(".moreSeller")[0];
            if(moreSeller){
            _src_urls.push(['clickBlock', {'tar':moreSeller, 'pxl': sc_data_source}]);
            }
        }
    }else{
        _src_urls.push(['click', {'tar':document.getElementById("buyMultiVendorBox"), 'pxl': sc_data_source}]);
        _src_urls.push(['click', {'tar':document.getElementById("BuyButton-1"), 'pxl': sc_data_source}]);
        _src_urls.push(['click', {'tar':document.getElementById("quickBuy-1"), 'pxl': sc_data_source}]);
    }
    _atm_params._atm_client_id = _atm_client_id;

    (function (p, a) {
        var w = window, d = document;
        var pr = ('https:' === d.location.protocol ? 'https:' : 'http:');
        var Ka = function(tar, evt, fnc, cpt){
            tar.addEventListener ? tar.addEventListener(evt, fnc, !!cpt) : tar.attachEvent && tar.attachEvent("on" + evt, fnc);
        };
        function callAffApp(){
            var bool = pr === 'https:',
                affinity_url = ( bool ? '//px.ph.affinity.com/affretarg_sets.gif?type=komli2&expire=604800' : '//px.ph.affinity.com/affretarg_set.gif?type=komli2&expire=604800'),
                appnexus_url = ( bool ? '//secure.adnxs.com/seg?add=1633637&t=2' : '//ib.adnxs.com/seg?add=1633637&t=2');
            var img1 = d.createElement("img");
                img1.src = pr + affinity_url;
            var img2 = d.createElement("img");
                img2.src = pr + appnexus_url;
        };
        var callToPP = function () {
            setTimeout(function () {
                if (p) {
                    var pi, po, ks, img, obj;
                        fpix = function(pxl){var img = d.createElement("img");img.src = pr + this.pxl; callAffApp();};
                    for (pi = 0; pi < p.length; pi++) {
                        po = p[pi];
                        if (po.length > 1) {
                            if (po[0] === 'script') {
                                ks = d.createElement('script');
                                ks.type = "text/javascript";
                                ks.src = pr + po[1];
                                d.body.appendChild(ks);
                            } else if (po[0] === 'image') {
                                img = d.createElement("img");
                                img.src = pr + po[1];
                            } else if(po[0] === 'click'){
                                obj = po[1];
                                if(obj.tar !== null){
                                    obj.tar.pxl = obj.pxl;  
                                    Ka(obj.tar, 'click', function(e){
                                       fpix.call({
                                            pxl: e.currentTarget ? e.currentTarget.pxl : e.srcElement.pxl
                                        });
                                    }, false);
                                }
                            }else if(po[0] === 'clickBlock'){
                                obj = po[1];
                                if(obj.tar !== null){
                                    obj.tar.pxl = obj.pxl;
                                    Ka(obj.tar, 'click', function(e){
                                       var eTarget = e.target ? e.target : e.srcElement;
                                       if(eTarget.tagName.toLowerCase() === "button"){
                                       fpix.call({
                                            pxl: e.currentTarget ? e.currentTarget.pxl : e.srcElement.pxl
                                        });
                                   }
                                    }, false);
                                }
                            }
                        }
                    }
                }
            }, 0);
        };
        if (a === 0) {
            callToPP();
        } else if (a === 1) {
            if (d.readyState && d.readyState === "complete") {
                callToPP();
            } else {
                if (w.addEventListener) {
                    w.addEventListener('load', callToPP, false);
                } else if (w.attachEvent) {
                    w.attachEvent('onload', callToPP);
                }
            }
        }
    }(_src_urls, 1));
}());
