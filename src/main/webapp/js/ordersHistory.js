var tableData = $("#ordersHistoryTable").find('tbody tr td');
var today = new Date();
var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();

for (var i = 0; i < tableData.length; i += 6) {
    var createdDate = tableData[i],
     beginDate = tableData[i + 2],
     endDate = tableData[i + 3],
     tmp = beginDate.innerHTML.split(" "),
     beginDateD = new Date(Date.parse(tmp));

    tableData[i + 2].innerHTML = tmp[0];
    tmp = endDate.innerHTML.split(" ");
    tableData[i + 3].innerHTML = tmp[0];
    tmp = createdDate.innerHTML.split(" ");
    tableData[i].innerHTML = tmp[0];

    if (beginDateD <= Date.parse(date)) {
        tableData[i + 5].innerHTML = "";
    }
}