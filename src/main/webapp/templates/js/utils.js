var listenOnRowClick = function() {
    $("#table-data").on("click", ".clickable-row", function (event) {
        let currentRow = $(this);
        window.selectedRowId = currentRow[0].dataset.id;
        currentRow.addClass("active-row").siblings().removeClass("active-row");
        enableDeleteButton();
        enableEditButton();
    });
};

let enableDeleteButton = function() {
    $("#delete-button").removeClass("disabled");
};

let enableEditButton = function() {
    $("#edit-button").removeClass("disabled");
};

let listenOnDeleteButtonClick = function(url) {
    $("#delete-button").on("click", function (event) {
        event.preventDefault();
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

let listenOnEditButtonClick = function(url) {
    $("#edit-button").on("click", function (event) {
        event.preventDefault();
        if (!window.selectedRowId) {
            return;
        }
        document.location = url + "?id=" + window.selectedRowId;
    })
};

let listenOnAddButtonClick = function(url) {
    $("#add-button").on("click", function (event) {
        event.preventDefault();
        document.location = url;
    })
};

let listenOnSubmitButton = function(url) {
    let editForm = $("#edit-form");
    editForm.submit(function(event) {
        event.preventDefault();
        $.ajax({
            url: editForm.attr("action"),
            type: 'POST',
            data: editForm.serialize(),
            success: function () {
                document.location = url;
            }
        })});
};

