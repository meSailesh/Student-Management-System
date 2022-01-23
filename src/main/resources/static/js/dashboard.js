Dropzone.autoDiscover = false;
$(document).ready( function () {
    $('#student-table').DataTable();


    $(".delete_student_button").on("click", function(e) {
        e.preventDefault();
        console.log("inside");

        var id = $(this).attr("data-id");
        var href =$("#delete_button_modal").attr("href").replace("_id_", id);
        $("#delete_button_modal").attr("href", href);


        $("#delete_student_modal").modal("show");

    });

    $("#submit").hide();
    $("#error").hide();
    $("#success").hide();


    $("#my-dropzone").dropzone({
        url: "/api/v1/upload-student",
        autoProcessQueue: false,
        acceptedFiles: ".csv",
        uploadMultiple: false,
        init : function () {
            var submitButton = $("#submit");
            myDropzone = this;

            submitButton.on("click", function () {
                myDropzone.processQueue();
            })

            this.on("addedfile", function() {
                $("#submit").show();
            });


        },

        error: function (file, response) {

            $("#error").html("Sorry, we could not parse your file. Please confirm it is in correct format").show();

            
        },

        success: function (file, response) {

            $("#success").html("Student created successfully from file").show();


        }
    });
} );