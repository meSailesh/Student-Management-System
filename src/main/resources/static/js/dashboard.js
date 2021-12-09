$(document).ready( function () {
    $('#student-table').DataTable();

    $("#deleteModalButton").on("click", function(e) {
        e.preventDefault();

        var id = $(this).attr("data-id");
        var href =$("#delete_btn").attr('href').replace('_id_', id);
        $("#delete_btn").attr('href', href);
        $("#deleteModal").modal("show");

    });
} );