var tableData = $("#ordersHistoryTable").find('tbody tr td');

for (var i = 0; i <= tableData.length; i += 6) {
    var date = tableData[i];
    var beginDate = tableData[i + 2];
    var endDate = tableData[i + 3];
    var tmp = beginDate.innerHTML.split(" ");
    tableData[i + 2].innerHTML = tmp[0];
    tmp = endDate.innerHTML.split(" ");
    tableData[i + 3].innerHTML = tmp[0];
    tmp = date.innerHTML.split(" ");
    tableData[i].innerHTML = tmp[0];
}