var listenOnRowClick = function() {
    $("#table-data").on("click", ".clickable-row", function (event) {
        let currentRow = $(this);
        window.selectedRowId = currentRow[0].dataset.id;
        currentRow.addClass("active-row").siblings().removeClass("active-row");
        enableDeleteButton();
    });
};

let enableDeleteButton = function() {
    $("#delete-button").removeClass("disabled");
};

var listenOnDeleteButtonClick = function(pkAttributeName, url) {
    $("#delete-button").on("click", function (event) {
        if (!window.selectedRowId) {
            return;
        }
        let xhr = new XMLHttpRequest();
        xhr.onload = function (e) {
            document.location.reload();
        };
        xhr.open("DELETE", url + window.selectedRowId, true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send();
    })
};