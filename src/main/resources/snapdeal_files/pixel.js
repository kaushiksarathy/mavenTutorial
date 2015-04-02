// declare global variables
var SPP = cc('_SPP'),
    SPS = cc('_SPS'),
    SRID = cc('srid'),
    e = encodeURIComponent,
    url = window.location.href,
    tld = window.location.origin,
    ref = window.document.referrer,
    sys = window.navigator.platform;

// initalize the functions
function init() {
    if (SPP == "NP") {
        initCookie("_SPP", "SPP", 365);
        send('u');
    } else if (SPS == "NP") {
        initCookie("_SPS", "SPS");
        send('u');
    } else {
        send('v');
    }
}
window.onload = init;

// send request regarding the user type
function send(u) {
    //var s = "http://54.243.149.109:8085/receiver?acc=" + _spp + "&u=" + u + "&SPP=" + cc('_SPP') + "&SPS=" + cc('_SPS') + "&SRID=" + cc('srid') + "&url=" + e(url) + "&ref=" + e(ref) + "&sys=" + e(sys) + "&tld=" + e(tld);
    var s = "http://pix.silverpush.co/receiver?acc=" + _spp + "&u=" + u + "&SPP=" + cc('_SPP') + "&SPS=" + cc('_SPS') + "&SRID=" + cc('srid') + "&url=" + e(url) + "&ref=" + e(ref) + "&sys=" + e(sys) + "&tld=" + e(tld);
    var xhr = initReq(s);
    if (!xhr) return;
    xhr.onload = function() {
        var text = xhr.responseText
    };
    xhr.onerror = function() {};
    xhr.send()
}

// checks for cookie by the 'name'
function cc(name) {
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(name + "=");
        if (c_start != -1) {
            c_start = c_start + name.length + 1;
            c_end = document.cookie.indexOf(";", c_start);
            if (c_end == -1) c_end = document.cookie.length;
            return unescape(document.cookie.substring(c_start, c_end))
        } else {
            return "NP"
        }
    } else {
        return "NP"
    }
}

// initializes a cookie with 'name' and its 'value' and 'days' to expire
function initCookie(name, value, days) {
    var time = new Date;
    if (value) value = value + "." + Math.random() * time;
    if (days) {
        time.setTime(time.getTime() + days * 24 * 60 * 60 * 1E3);
        var expires = "; expires=" + time.toGMTString()
    } else var expires = "";
    document.cookie = name + "=" + value + expires + "; path=/";
}

// initaliezes a CORS request
function initReq(url) {
    var xhr = new XMLHttpRequest;
    if ("withCredentials" in xhr) xhr.open('POST', url, true);
    else if (typeof XDomainRequest != "undefined") {
        xhr = new XDomainRequest;
        xhr.open(method, url)
    } else xhr = null;
    return xhr
}