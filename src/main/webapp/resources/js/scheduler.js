$(function () {
    var tbl = $("#tblList").DataTable({
        "language": {
            "paginate": {"first": "&#10094;&#10094;", "previous": "&#10094;", "next": "&#10095;", "last": "&#10095;&#10095;"},
            "search": "S<span accesskey='E'><b><u>e</u></b></span>arch</label>",
            "processing": "<div><img src='resources/images/loading.gif' alt='Please Wait' style='height:32px; width:32px;'/><p style='color:black;'>Please wait...</p></div>",
            "lengthMenu": "Show _MENU_ doctors",
            "info": "Showing _START_ to _END_ of _TOTAL_ doctor(s)",
            "zeroRecords": "No matching doctor(s) found",
            "infoEmpty": "No doctor(s) found",
            "infoFiltered": "(filtered from _MAX_ doctor(s))"
        },
        "paginationType": "full_numbers",
        "dom": "<'row'<'col-sm-4'f><'col-sm-4'B><'col-sm-4'p>><'row'<'col-sm-12'tr>><'row'<'col-sm-4'i><'col-sm-4'l><'col-sm-4'p>>",
        "lengthMenu": [[25, 50, 100, 200, -1], ['25', '50', '100', '200', 'All']],
        "start ": 25,
        "length": 25,
        "processing": true,
        "serverSide": true,
        "autoWidth": false,
        "lengthChange": true,
        "fixedHeader": true,
        "stateSave": true,
        "scrollX": false,
        "deferRender": true,
        "rowId": "id",
        "ajax": {
            "url": "fetchSchedules.json",
            "type": "POST",
            "timeout": "10000"
        },
        "columns": [
            {"data": null, "targets": "0", "orderable": false, "searchable": false, "class": "rightAlign", "width": "5%", "render": function (field, type, row, meta) {
                    return meta.row + meta.settings._iDisplayStart + 1;
                }},
            {"data": "doctor.name", "targets": "1", "orderable": true, "searchable": true, "width": "45%", "render": function (field, type, row, meta) {
                    return field;
                }},
            {"data": "doctor.phone1", "targets": "2", "orderable": true, "searchable": true, "width": "10%", "render": function (field, type, row, meta) {
                    return field;
                }},
            {"data": "arrivalRefNo", "targets": "3", "orderable": true, "searchable": false, "width": "15%", "render": function (field, type, row, meta) {
                    return field;
                }},
            {"data": "arrivalDate", "targets": "4", "orderable": true, "searchable": false, "class": "rightAlign", "width": "15%", "render": function (field, type, row, meta) {
                    return formatDate(field);
                }},
            {"data": "hotelName", "targets": "5", "orderable": true, "searchable": false, "width": "10%", "render": function (field, type, row, meta) {
                    return field;
                }
            }
        ],
        "order": [[1, "asc"]],
        "buttons": [{"text": "&#9776;", "extend": "colvis", "columns": ":gt(1)", "className": "link"},
            {"extend": "excelHtml5", "exportOptions": {"columns": ":visible"}, "text": "Export to E<span accesskey='X'><b><u>x</u></b></span>cel", "className": "link"},
            {"text": "<span accesskey='D'><b><u>D</u></b></span>octor", "action": function (e, dt, node, config) {
                    window.open("doctor.htm");
                }, "className": "link"},
            {"text": "<span accesskey='R'><b><u>R</u></b></span>SM", "action": function (e, dt, node, config) {
                    window.open("user.htm");
                }, "className": "link"},
            {"text": "Doctor <span accesskey='S'><b><u>S</u></b></span>chedule", "action": function (e, dt, node, config) {
                    window.open("scheduler.htm");
                }, "className": "link"},
            {"text": "Eve<span accesskey='N'><b><u>n</u></b></span>t", "action": function (e, dt, node, config) {
                    window.open("event.htm");
                }, "className": "link"}
        ]
//            {"extend": "excelHtml5","exportOptions":{"columns":":visible"}, "text": "Export to E<span accesskey='X'><b><u>x</u></b></span>cel"}

    });

    $('div.dataTables_filter input').attr('title', 'Press enter to filter');
    $('div.dataTables_filter input').focus();
    $('div.dataTables_filter input').unbind();
    $('div.dataTables_filter input').bind('keyup change', function (e) {
        if (e.keyCode === 13) {
            tbl.search($(this).val()).draw();
        }
    });
});

