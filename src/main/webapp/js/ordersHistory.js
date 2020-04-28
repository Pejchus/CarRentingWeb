var tableData = $("#ordersHistoryTable").find('tbody tr td');

for (var i = 1; i <= tableData.length; i += 4) {
    var beginDate = tableData[i];
    var endDate = tableData[i + 1];
    var tmp = beginDate.innerHTML.split(" ");
    tableData[i].innerHTML = tmp[0];
    tmp = endDate.innerHTML.split(" ");
    tableData[i + 1].innerHTML = tmp[0];
}