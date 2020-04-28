var carID = getUrlParameter('id');
var reservationDates = [];
$.ajax( {
    method : "GET",
    url : "/carOrders",
    dataType : 'json',
    contentType: "application/json",
    timeout : 10000,
    data :  {
        "carID" : carID
    },
    success : function (data) {
        updateCalendar(data);
    }
});

var tripStartDate = -1;

function updateCalendar(data) {
    jQuery(document).ready(
        (function ($) {
            function dayAvailable(date) {
                var dateString = jQuery.datepicker.formatDate('yy-mm-dd', date);
                var dateRange = [];
                for (var i = 0; i < data.length; i++) {
                    var startDate = data[i][0].split(" ")[0],
                        endDate  = data[i][1].split(" ")[0];
                    for (var d = new Date(startDate); d <= new Date(endDate); d.setDate(d.getDate() + 1)) {
                        dateRange.push($.datepicker.formatDate('yy-mm-dd', d));
                    }
                    reservationDates[i] = dateRange;
                }
                return [dateRange.indexOf(dateString) == -1];
            }

            $('#tripstart').datepicker({
                beforeShowDay: dayAvailable,
                minDate: 0,
                onSelect: function (date) {
                    var selectedDate = new Date(date);
                    tripStartDate = selectedDate;
                    var msecsInADay = 86400000;
                    var endDate = new Date(selectedDate.getTime());
                    var maxDate;
                    console.log(reservationDates);
                    for (var i = 0; i < reservationDates[0].length; i++) {
                        if (selectedDate < Date.parse(reservationDates[0][i])) {
                            maxDate = new Date(Date.parse(reservationDates[0][i]) - msecsInADay);
                            break;
                        }
                    }
                    $("#tripend").datepicker("option", "minDate", endDate);
                    $("#tripend").datepicker("option", "maxDate", maxDate);
                }
            });

            $('#tripend').datepicker({
                beforeShowDay: dayAvailable,
                onSelect: function (date) {
                    if (tripStartDate !== -1) {
                        var tripEndDate = new Date(date);
                        var msecsInADay = 86400000;
                        var priceDay = $("#priceDay").html();
                        var priceTotal = priceDay * ((Number(tripEndDate.getTime() - tripStartDate.getTime()) / msecsInADay) + 1);
                        $("#totalPrice").html(priceTotal);
                    } else {
                        $("#tripendMsg").html("Vyberte prosim prvne datum od jinak je mozne ze vas vyber bude prepsan");
                    }
                }
            });
        })(jQuery)
    );
}

function getUrlParameter(sParam) {
    var url = window.location.href;
    if (url.includes("carProfile")) {
        var sPageURL = window.location.search.substring(1),
            sURLVariables = sPageURL.split('?'),
            sParameterName,
            i;
        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split('=');
            if (sParameterName[0] === sParam) {
                return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
            }
        }
    }
    else if (url.includes("makeOrder")) {
        var sPageURL = window.location.search.substring(1),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;
        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split('=');
            if (sParameterName[0] === "carId") {
                console.log("aaa")
                return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
            }
        }
    }
};

function getTime(d) {
    return new Date(d.split("-").reverse().join("-")).getTime();
}