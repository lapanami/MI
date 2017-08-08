var dialogOpening = false;

function getDialog() {
    $('#popupWindows').dialog({ autoOpen: false, modal: true, width: 'auto' });
    return $('#popupWindows');
}

function closeDialog() {
    var d = getDialog();
    if (d)
        getDialog().dialog("close");

    dialogOpening = false;
}

function resizeOnRefresh() {
    var winContent = $('.t-window-content');
    winContent.css("width", "");
    winContent.css("height", "");
}

function refreshGrid() {
    var table = $.fn.dataTable.fnTables(true);
    $(table).each(function () {
        this.dataTable().fnReloadAjax();
    });
}

function refreshGridByName(grid_name) {
    var oTable = $('#' + grid_name).dataTable();
    oTable.fnReloadAjax();
}

function closeWindow() {
    var d = getDialog();
    if (d) {
        getDialog().content(" ");
    }
}

function ShowPopup(title, content) {
    var window = getDialog();
    window.html(content);
    window.dialog("open");
    window.dialog({ title: title });
    dialogOpening = true;
}

function ShowPopup(title, content, width, height) {
    var winContent = $('.t-window-content');
    winContent.css("width", width);
    winContent.css("height", height);
    
    var window = getDialog();
    window.html(content);
    window.dialog("open");
    window.dialog({ title: title });
    dialogOpening = true;
}

function LoadPopup(url, data, title) {
    $.ajax({
        url: url,
        data: data,
        cache: false,
        success: function (content) {
            //alert(title);
            ShowPopup(title, content);
        },
        error: function () {
            //alert('fail LoadPopup: ' + url);
        }
    });
}

function LoadPopup(url, data, title, width, height) {
    $.ajax({
        url: url,
        data: data,
        cache: false,
        success: function (content) {
            ShowPopup(title, content, width, height);
        },
        error: function () {
            //alert('fail LoadPopup: ' + url);
        }
    });
}

function onSubmit(e) {
    if (typeof CKEDITOR != 'undefined' && CKEDITOR) {
        for (instance in CKEDITOR.instances)
            CKEDITOR.instances[instance].updateElement();
    }

    var form = $(e).closest("form")[0];
    if ($(form).valid()) {
        $.ajax({
            url: form.action,
            type: form.method,
            data: $(form).serialize(),
            success: UpdateSuccess,
            error: function () {
                //alert('fail onSubmit: ' + form.action);
            }
        });
    }
    return false;
}

function onSubmitThis(form_id) {
    if (typeof CKEDITOR != 'undefined' && CKEDITOR) {
        for (instance in CKEDITOR.instances)
            CKEDITOR.instances[instance].updateElement();
    }

    var form = form_id ? $("#" + form_id)[0] : $("form")[0];
    if ($(form).valid()) {
        $.ajax({
            url: form.action,
            type: form.method,
            data: $(form).serialize(),
            success: UpdateSuccess,
            error: function () { //alert('fail onSubmitThis: ' + form.action); 
            }
        });
    }
    return false;
}

function onSubmitNormal(form_id) {
    var form = form_id ? $("#" + form_id) : $("form");
    var val = form.validate();
    val.form();
    if (val.valid())
        form[0].submit();

    return false;
}

function UpdateSuccess(e) {
    if (e.stay == true) {
        if (e.response) {
            UpdateMessagePop(e.response, e.type);
        }
        if (e.grid_name) {
            if (e.grid_name == "all") {
                refreshGrid();
            }
            else {
                refreshGridByName(e.grid_name);
            }
        }
    }
    else {
        if (e.response) {
            UpdateMessage(e.response, e.type);
        }
        closeDialog();

        if (e.reload == true) {
            if (e.redirect) {
                window.location = e.redirect;
            }
            else {
                //window.location = window.location;
                window.location.reload();
            }
        }

        if (e.grid_name) {
            if (e.grid_name == "all") {
                refreshGrid();
            }
            else {
                refreshGridByName(e.grid_name);
            }
        }

        if (e.executefn) {
            var fn = window[e.executefn];
            if (e.fnparams) {
                if (typeof fn === "function") fn(e.fnparams);
            }
            else {
                if (typeof fn === "function") fn();
            }
        }
    }

    if (e.loading) {
        $("#" + e.loading).hide();
        $("#div_overlay").removeClass("dev_overlay_effect");
    }

    //hide_loading();
}

function UpdateMessage(mess, type) {
    if (mess != "") {
        var cssclass = "";
        var imgUrl = "";
        if (type == "error") {
            cssclass = "dev_notify_message_error";
            imgUrl = contextPath + "images/alert32.png";
        }
        else if (type == "success") {
            cssclass = "dev_notify_message_success";
            imgUrl = contextPath + "images/success_20.gif";
        }
        else if (type == "inform") {
            cssclass = "dev_notify_message_inform";
            imgUrl = contextPath + "images/info_20.gif";
        }

        $("#div_notify").show();
        $("#div_css_notify").removeAttr("class");
        $("#div_css_notify").addClass(cssclass);
        $("#img_notify").attr("src", imgUrl);
        $("#div_notify_message").html(mess);

        $("#server_message").html("");
        $("#server_message").hide();

        HideInPeriod("div_notify", 30000);
    }
    else {
        $("#div_notify").hide();
        $("#div_css_notify").removeClass(cssclass);
        $("#img_notify").removeAttr("src");
        $("#div_notify_message").html("");
    }
}

function UpdateMessagePop(mess, type) {
    if (mess != "") {
        var cssclass = "";
        var imgUrl = "";
        if (type == "error") {
            cssclass = "dev_notify_message_error";
            imgUrl = contextPath + "images/alert32.png";
        }
        else if (type == "success") {
            cssclass = "dev_notify_message_success";
            imgUrl = contextPath + "images/success_20.gif";
        }
        else if (type == "inform") {
            cssclass = "dev_notify_message_inform";
            imgUrl = contextPath + "images/info_20.gif";
        }

        $("#div_notify_pop").show();
        $("#div_css_notify_pop").removeAttr("class");
        $("#div_css_notify_pop").addClass(cssclass);
        $("#img_notify_pop").attr("src", imgUrl);
        $("#div_notify_message_pop").html(mess);

        HideInPeriod("div_notify_pop", 30000);
    }
    else {
        $("#div_notify_pop").hide();
        $("#div_css_notify_pop").removeClass(cssclass);
        $("#img_notify_pop").removeAttr("src");
        $("#div_notify_message_pop").html("");
    }
}

function HideInPeriod(id, period) {
    setTimeout(function () {
        $("#" + id).fadeOut("slow")
    }, period);
}

function numbersonly(e, isdecimal, txt) {
    var key;
    var keychar;

    if (window.event) {
        key = window.event.keyCode;
    }
    else if (e) {
        key = e.which;
    }
    else {
        return true;
    }
    keychar = String.fromCharCode(key);

    if (keychar == "." && ((txt.value.split(".").length - 1) == 1 || txt.value == "")) {
        return false;
    }
    else if ((key == null) || (key == 0) || (key == 8) || (key == 9) || (key == 13) || (key == 27)) {
        return true;
    }
    else if ((("0123456789").indexOf(keychar) > -1)) {
        return true;
    }
    else if (isdecimal && (keychar == ".")) {
        return true;
    }
    else
        return false;
}

function ToCurrencyString(num) {
    num = num.toString().replace(/\$|\,/g, '');
    if (isNaN(num))
        num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num * 100 + 0.50000000001);
    cents = num % 100;
    num = Math.floor(num / 100).toString();
    if (cents < 10)
        cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
        num = num.substring(0, num.length - (4 * i + 3)) + ',' +
    num.substring(num.length - (4 * i + 3));
    var result_num = (((sign) ? '' : '-') + num + '.' + cents);
    if (result_num == "0.00") {
        result_num = "";
    }
    return result_num;
}

function ToCurrencyZeroString(num) {
    num = num.toString().replace(/\$|\,/g, '');
    if (isNaN(num))
        num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num * 100 + 0.50000000001);
    cents = num % 100;
    num = Math.floor(num / 100).toString();
    if (cents < 10)
        cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
        num = num.substring(0, num.length - (4 * i + 3)) + ',' +
    num.substring(num.length - (4 * i + 3));
    return (((sign) ? '' : '-') + num + '.' + cents);
}

function ToCurrencyNoZeroString(num) {
    num = num.toString().replace(/\$|\,/g, '');
    if (isNaN(num))
        num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num * 100 + 0.50000000001);
    cents = num % 100;
    num = Math.floor(num / 100).toString();
    if (cents < 10)
        cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
        num = num.substring(0, num.length - (4 * i + 3)) + ',' +
    num.substring(num.length - (4 * i + 3));
    return (((sign) ? '' : '-') + num);
}

