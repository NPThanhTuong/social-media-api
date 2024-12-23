/*
Template: SocialV - Responsive Bootstrap 4 Admin Dashboard Template
Author: iqonicthemes.in
Design and Developed by: iqonicthemes.in
NOTE: This file contains the styling for responsive Template.
*/

/*----------------------------------------------
Index Of Script
------------------------------------------------

:: Tooltip
:: right-sidebar
:: Scrollbar
:: Sidebar main
:: Search input
:: chat
:: todo
:: page-loader
:: Mailbox
:: Editable Table


------------------------------------------------
Index Of Script
----------------------------------------------*/

(function(jQuery) {

  "use strict";

  jQuery(document).ready(function() {

    /*---------------------------------------------------------------------
    Tooltip
    -----------------------------------------------------------------------*/
        jQuery('[data-bs-toggle="popover"]').popover();
        jQuery('[data-bs-toggle="tooltip"]').tooltip();

        /*---------------------------------------------------------------------
        range Button 
        -----------------------------------------------------------------------*/
        function wcqib_refresh_quantity_increments() {
            jQuery("div.quantity:not(.buttons_added), td.quantity:not(.buttons_added)").each(function(a, b) {
                var c = jQuery(b);
                c.addClass("buttons_added"), c.children().first().before('<input type="button" value="-" class="minus" />'), c.children().last().after('<input type="button" value="+" class="plus" />')
            })
        }
        String.prototype.getDecimals || (String.prototype.getDecimals = function() {
            var a = this,
                b = ("" + a).match(/(?:\.(\d+))?(?:[eE]([+-]?\d+))?$/);
            return b ? Math.max(0, (b[1] ? b[1].length : 0) - (b[2] ? +b[2] : 0)) : 0
        }), jQuery(document).ready(function() {
            wcqib_refresh_quantity_increments()
        }), jQuery(document).on("updated_wc_div", function() {
            wcqib_refresh_quantity_increments()
        }), jQuery(document).on("click", ".plus, .minus", function() {
            var a = jQuery(this).closest(".quantity").find(".qty"),
                b = parseFloat(a.val()),
                c = parseFloat(a.attr("max")),
                d = parseFloat(a.attr("min")),
                e = a.attr("step");
            b && "" !== b && "NaN" !== b || (b = 0), "" !== c && "NaN" !== c || (c = ""), "" !== d && "NaN" !== d || (d = 0), "any" !== e && "" !== e && void 0 !== e && "NaN" !== parseFloat(e) || (e = 1), jQuery(this).is(".plus") ? c && b >= c ? a.val(c) : a.val((b + parseFloat(e)).toFixed(e.getDecimals())) : d && b <= d ? a.val(d) : b > 0 && a.val((b - parseFloat(e)).toFixed(e.getDecimals())), a.trigger("change")
        });
    /*---------------------------------------------------------------------
        right-sidebar
    -----------------------------------------------------------------------*/
        ! function(e) {
            "use strict";

            function t(t) {
                t ? e(".right-sidebar-mini").addClass("right-sidebar") : e(".right-sidebar-mini").removeClass("right-sidebar")
                t ? e("body").addClass("right-sidebar-close") : e("body").removeClass("right-sidebar-close")
            }
            e(document).ready(function() {
                var a = !1;
                t(a), e(document).on("click", ".right-sidebar-toggle", function() {
                    t(a = !a)
                })
            })
        }(jQuery);
    /*---------------------------------------------------------------------
        Sidebar main
    -----------------------------------------------------------------------*/
        var parents = jQuery('li.active').parents('.iq-submenu.collapse');
        parents.addClass('show');

        parents.parents('li').addClass('active');
        jQuery( 'li.active > a[aria-expanded="false"]').attr('aria-expanded', 'true');

    /*---------------------------------------------------------------------
        Page Menu
    -----------------------------------------------------------------------*/
        jQuery(document).on('click', '.wrapper-menu', function() {
            jQuery(this).toggleClass('open');
        });

        jQuery(document).on('click', ".wrapper-menu", function() {
            jQuery("body").toggleClass("sidebar-main");
        });

    /*---------------------------------------------------------------------
        Scrollbar
    -----------------------------------------------------------------------*/
        let Scrollbar = window.Scrollbar;
        if (jQuery('#sidebar-scrollbar').length) {
            Scrollbar.init(document.querySelector('#sidebar-scrollbar'),
            {
                continuousScrolling: false,
              });
        }
        let Scrollbar1 = window.Scrollbar;

         if (jQuery('[data-scrollbar="init"]').length) {

          Scrollbar1.init(document.querySelector('[data-scrollbar="init"]'), { continuousScrolling: false });

     }
    /*---------------------------------------------------------------------
    chatuser
    -----------------------------------------------------------------------*/
        jQuery(document).on('click', '.chat-head .chat-user-profile', function() {
            jQuery(this).parent().next().toggleClass('show');
        });
        jQuery(document).on('click', '.user-profile .close-popup', function() {
            jQuery(this).parent().parent().removeClass('show');
        });

    /*---------------------------------------------------------------------
    chatuser main
    -----------------------------------------------------------------------*/
        jQuery(document).on('click', '.chat-search .chat-profile', function() {
            jQuery(this).parent().next().toggleClass('show');
        });
        jQuery(document).on('click', '.user-profile .close-popup', function() {
            jQuery(this).parent().parent().removeClass('show');
        });

    /*---------------------------------------------------------------------
    Chat start
    -----------------------------------------------------------------------*/
        jQuery(document).on('click', '#chat-start', function() {
            jQuery('.chat-data-left').toggleClass('show');
        });
        jQuery(document).on('click', '.close-btn-res', function() {
            jQuery('.chat-data-left').removeClass('show');
        });
        jQuery(document).on('click', '.iq-chat-ui li', function() {
            jQuery('.chat-data-left').removeClass('show');
        });
        jQuery(document).on('click', '.sidebar-toggle', function() {
            jQuery('.chat-data-left').addClass('show');
        });
    /*---------------------------------------------------------------------
        todo Page
    -----------------------------------------------------------------------*/
        jQuery(document).on('click', '.todo-task-list > li > a', function() {
            jQuery('.todo-task-list li').removeClass('active');
            jQuery('.todo-task-list .sub-task').removeClass('show');
            jQuery(this).parent().toggleClass('active');
            jQuery(this).next().toggleClass('show');
        });
        jQuery(document).on('click', '.todo-task-list > li li > a', function() {
            jQuery('.todo-task-list li li').removeClass('active');
            jQuery(this).parent().toggleClass('active');
        });


     /*---------------------------------------------------------------------
           checkout
        -----------------------------------------------------------------------*/

        jQuery(document).ready(function(){
            jQuery('#place-order').click(function(){
                jQuery('#cart').removeClass('show');
                jQuery('#address').addClass('show');
            });
            jQuery('#deliver-address').click(function(){
                jQuery('#address').removeClass('show');
                jQuery('#payment').addClass('show');
            });
        });

        /*---------------------------------------------------------------------
            Page Loader
        -----------------------------------------------------------------------*/
        jQuery("#load").fadeOut();
        jQuery("#loading").delay().fadeOut("");

        /*---------------------------------------------------------------------
        Mailbox
        -----------------------------------------------------------------------*/
        jQuery(document).on('click', 'ul.iq-email-sender-list li', function(e) {
            if (e.target.closest('.email-app-details') === null) {
                jQuery(this).find('.email-app-details').addClass('show');
            }
        });

        jQuery(document).on('click', '.email-remove', function(e) {
            jQuery(this).closest('.email-app-details').removeClass('show');
        });

        /*---------------------------------------------------------------------
        Confirm Button
        -----------------------------------------------------------------------*/

        // $(document).ready(function () {
        //     $(".confirm-btn").click(function () {
        //         $(".request-btn").toggle();
        //         $(".confirm-btn").last().remove();
        //     });
        // });

        $('.confirm-btn').on('click',function() {
            $(this).closest('.confirm-click-btn').find('.request-btn').hide()
        })

        /*---------------------------------------------------------------------
        Select input
        -----------------------------------------------------------------------*/
        jQuery('.select2jsMultiSelect').select2({
            tags: true
        });

        /*---------------------------------------------------------------------
        Fieldset
        -----------------------------------------------------------------------*/

        $(document).ready(function() {
            var e, t, a, n, o = 1,
                r = $("fieldset").length;

            function i(e) {
                var t = parseFloat(100 / r) * e;
                t = t.toFixed(), $(".progress-bar").css("width", t + "%")
            }
            i(o), $(".next").click(function() {
                e = $(this).parent(), t = $(this).parent().next(), $("#top-tab-list li").eq($("fieldset").index(t)).addClass("active"), $("#top-tab-list li").eq($("fieldset").index(e)).addClass("done"), t.show(), e.animate({
                    opacity: 0
                }, {
                    step: function(a) {
                        n = 1 - a, e.css({
                            display: "none",
                            position: "relative"
                        }), t.css({
                            opacity: n
                        })
                    },
                    duration: 500
                }), i(++o)
            }), $(".previous").click(function() {
                e = $(this).parent(), a = $(this).parent().prev(), $("#top-tab-list li").eq($("fieldset").index(e)).removeClass("active"), $("#top-tab-list li").eq($("fieldset").index(a)).removeClass("done"), a.show(), e.animate({
                    opacity: 0
                }, {
                    step: function(t) {
                        n = 1 - t, e.css({
                            display: "none",
                            position: "relative"
                        }), a.css({
                            opacity: n
                        })
                    },
                    duration: 500
                }), i(--o)
            }), $(".submit").click(function() {
                return !1
            })
        }), $(document).ready(function() {
            var e = $("div.setup-panel div a"),
                t = $(".setup-content"),
                a = $(".nextBtn");
            t.hide(), e.click(function(a) {
                a.preventDefault();
                var n = $($(this).attr("href")),
                    o = $(this);
                o.hasClass("disabled") || (e.addClass("active"), o.parent().addClass("active"), t.hide(), n.show(), n.find("input:eq(0)").focus())
            }), a.click(function() {
                var e = $(this).closest(".setup-content"),
                    t = e.attr("id"),
                    a = $('div.setup-panel div a[href="#' + t + '"]').parent().next().children("a"),
                    n = e.find("input[type='text'],input[type='email'],input[type='password'],input[type='url'],textarea"),
                    o = !0;
                $(".form-group").removeClass("has-error");
                for (var r = 0; r < n.length; r++) n[r].validity.valid || (o = !1, $(n[r]).closest(".form-group").addClass("has-error"));
                o && a.removeAttr("disabled").trigger("click")
            }), $("div.setup-panel div a.active").trigger("click")
        }), $(document).ready(function() {
            var e, t, a, n, o = 1,
                r = $("fieldset").length;

            function i(e) {
                var t = parseFloat(100 / r) * e;
                t = t.toFixed(), $(".progress-bar").css("width", t + "%")
            }
            i(o), $(".next").click(function() {
                e = $(this).parent(), t = $(this).parent().next(), $("#top-tabbar-vertical li").eq($("fieldset").index(t)).addClass("active"), t.show(), e.animate({
                    opacity: 0
                }, {
                    step: function(a) {
                        n = 1 - a, e.css({
                            display: "none",
                            position: "relative"
                        }), t.css({
                            opacity: n
                        })
                    },
                    duration: 500
                }), i(++o)
            }), $(".previous").click(function() {
                e = $(this).parent(), a = $(this).parent().prev(), $("#top-tabbar-vertical li").eq($("fieldset").index(e)).removeClass("active"), a.show(), e.animate({
                    opacity: 0
                }, {
                    step: function(t) {
                        n = 1 - t, e.css({
                            display: "none",
                            position: "relative"
                        }), a.css({
                            opacity: n
                        })
                    },
                    duration: 500
                }), i(--o)
            }), $(".submit").click(function() {
                return !1
            })
        }), $(document).ready(function() {
            $(".file-upload").on("change", function() {
                ! function(e) {
                    if (e.files && e.files[0]) {
                        var t = new FileReader;
                        t.onload = function(e) {
                            $(".profile-pic").attr("src", e.target.result)
                        }, t.readAsDataURL(e.files[0])
                    }
                }(this)
            }), $(".upload-button").on("click", function() {
                $(".file-upload").click()
            })
        }), $(function() {
            function e(e) {
                return e / 100 * 360
            }
            $(".progress-round").each(function() {
                var t = $(this).attr("data-value"),
                    a = $(this).find(".progress-left .progress-bar"),
                    n = $(this).find(".progress-right .progress-bar");
                t > 0 && (t <= 50 ? n.css("transform", "rotate(" + e(t) + "deg)") : (n.css("transform", "rotate(180deg)"), a.css("transform", "rotate(" + e(t - 50) + "deg)")))
            })
        });

        /*---------------------------------------------------------------------
              Vanila Datepicker
        -----------------------------------------------------------------------*/
        const datepickers = document.querySelectorAll('.vanila-datepicker')
        Array.from(datepickers, (elem) => {
        new Datepicker(elem)
        })
        const daterangePickers = document.querySelectorAll('.vanila-daterangepicker')
        Array.from(daterangePickers, (elem) => {
        new DateRangePicker(elem)
        })

        /*---------------------------------------------------------------------
        Form Validation
        -----------------------------------------------------------------------*/

        // Example starter JavaScript for disabling form submissions if there are invalid fields
        window.addEventListener('load', function() {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function(form) {
                form.addEventListener('submit', function(event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);

        /*---------------------------------------------------------------------
        Form Wizard-validate
        -----------------------------------------------------------------------*/

        var registrationForm = $('#registration');
        if(registrationForm.length) {
            // var formValidate = registrationForm.validate({
                // errorClass: 'is-invalid',
                // errorPlacement: () => false
                // });

                const wizard = new Enchanter('registration', {}, {
                onNext: () => {
                    // if (!registrationForm.valid()) {
                    // formValidate.focusInvalid();
                    // return false;
                    // }
                }
                });
        }

        /*-----------------------------------------------------------------------------
        calender
        ------------------------------------------------------------------------------*/
        if (jQuery('#calendar1').length) {
            let calendarEl = document.getElementById('calendar1');
              let calendar1 = new FullCalendar.Calendar(calendarEl, {
                selectable: true,
                plugins: ["timeGrid", "dayGrid", "list", "interaction"],
                timeZone: "UTC",
                defaultView: "dayGridMonth",
                contentHeight: "auto",
                eventLimit: true,
                dayMaxEvents: 4,
                header: {
                    left: "prev,next today",
                    center: "title",
                    right: "dayGridMonth,timeGridWeek,timeGridDay,listWeek"
                },
                dateClick: function (info) {
                    $('#schedule-start-date').val(info.dateStr)
                    $('#schedule-end-date').val(info.dateStr)
                    $('#date-event').modal('show')
                },
                events: [
                    {
                        title: 'Click for Google',
                        url: 'http://google.com/',
                        start: moment(new Date(), 'YYYY-MM-DD').add(-20, 'days').format('YYYY-MM-DD') + 'T05:30:00.000Z',
                        color: '#50b5ff'
                    },
                    {
                        title: 'All Day Event',
                        start: moment(new Date(), 'YYYY-MM-DD').add(-18, 'days').format('YYYY-MM-DD') + 'T05:30:00.000Z',
                        color: '#a09e9e'
                    },
                    {
                        title: 'Long Event',
                        start: moment(new Date(), 'YYYY-MM-DD').add(-16, 'days').format('YYYY-MM-DD') + 'T05:30:00.000Z',
                        end: moment(new Date(), 'YYYY-MM-DD').add(-13, 'days').format('YYYY-MM-DD') + 'T05:30:00.000Z',
                        color: '#49f0d3'
                    },
                    {
                        groupId: '999',
                        title: 'Repeating Event',
                        start: moment(new Date(), 'YYYY-MM-DD').add(-14, 'days').format('YYYY-MM-DD') + 'T05:30:00.000Z',
                        color: '#ffba68'
                    },
                    {
                        groupId: '999',
                        title: 'Repeating Event',
                        start: moment(new Date(), 'YYYY-MM-DD').add(-12, 'days').format('YYYY-MM-DD') + 'T05:30:00.000Z',
                        color: '#d592ff '
                    },
                    {
                        groupId: '999',
                        title: 'Repeating Event',
                        start: moment(new Date(), 'YYYY-MM-DD').add(-10, 'days').format('YYYY-MM-DD') + 'T05:30:00.000Z',
                        color: '#ff9b8a'
                    },
                    {
                        title: 'Birthday Party',
                        start: moment(new Date(), 'YYYY-MM-DD').add(-8, 'days').format('YYYY-MM-DD') + 'T05:30:00.000Z',
                        color: '#49f0d3'
                    },
                    {
                        title: 'Meeting',
                        start: moment(new Date(), 'YYYY-MM-DD').add(-6, 'days').format('YYYY-MM-DD') + 'T05:30:00.000Z',
                        color: '#a09e9e'
                    },
                    {
                        title: 'Birthday Party',
                        start: moment(new Date(), 'YYYY-MM-DD').add(-5, 'days').format('YYYY-MM-DD') + 'T05:30:00.000Z',
                        color: '#49f0d3'
                    },
                    {
                        title: 'Birthday Party',
                        start: moment(new Date(), 'YYYY-MM-DD').add(-2, 'days').format('YYYY-MM-DD') + 'T05:30:00.000Z',
                        color: '#ff9b8a '
                    },

                    {
                        title: 'Meeting',
                        start: moment(new Date(), 'YYYY-MM-DD').add(0, 'days').format('YYYY-MM-DD') + 'T05:30:00.000Z',
                        color: '#ff9b8a'
                    },
                    {
                        title: 'Click for Google',
                        url: 'http://google.com/',
                        start: moment(new Date(), 'YYYY-MM-DD').add(0, 'days').format('YYYY-MM-DD') + 'T06:30:00.000Z',
                        color: '#d592ff'
                    },
                    {
                        groupId: '999',
                        title: 'Repeating Event',
                        start: moment(new Date(), 'YYYY-MM-DD').add(0, 'days').format('YYYY-MM-DD') + 'T07:30:00.000Z',
                        color: '#49f0d3'
                    },
                    {
                        title: 'Birthday Party',
                        start: moment(new Date(), 'YYYY-MM-DD').add(0, 'days').format('YYYY-MM-DD') + 'T08:30:00.000Z',
                        color: '#f4a965'
                    },
                    {
                        title: 'Doctor Meeting',
                        start: moment(new Date(), 'YYYY-MM-DD').add(0, 'days').format('YYYY-MM-DD') + 'T05:30:00.000Z',
                        color: '#f4a965'
                    },
                    {
                        title: 'All Day Event',
                        start: moment(new Date(), 'YYYY-MM-DD').add(1, 'days').format('YYYY-MM-DD') + 'T05:30:00.000Z',
                        color: ' #50b5ff'
                    },
                    {
                        groupId: '999',
                        title: 'Repeating Event',
                        start: moment(new Date(), 'YYYY-MM-DD').add(8, 'days').format('YYYY-MM-DD') + 'T05:30:00.000Z',
                        color: ' #50b5ff'
                    },
                    {
                        groupId: '999',
                        title: 'Repeating Event',
                        start: moment(new Date(), 'YYYY-MM-DD').add(10, 'days').format('YYYY-MM-DD') + 'T05:30:00.000Z',
                        color: '#49f0d3'
                    }
                ]
            });
            calendar1.render();
        }

        /*---------------------------------------------------------------------
        Sweet alert
        -----------------------------------------------------------------------*/
        $('[data-extra-toggle="delete"]').on('click', function(e) {
            const closestElem = $(this).attr('data-closest-elem')
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: 'btn btn-primary btn-lg',
                    cancelButton: 'btn btn-outline-primary btn-lg ms-2'
                },
                buttonsStyling: false
            })

            swalWithBootstrapButtons.fire({
                    title: 'Are you sure?',
                    text: "You won't be able to revert this!",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonText: 'Yes, delete it!',
                    showClass: {
                        popup: 'animate__animated animate__zoomIn'
                    },
                    hideClass: {
                        popup: 'animate__animated animate__zoomOut'
                    }
                })
                .then((willDelete) => {
                    if (willDelete.isConfirmed) {
                        swalWithBootstrapButtons.fire({
                            title: 'Deleted!',
                            text: "Your Request has been deleted.",
                            icon: 'success',
                            showClass: {
                                popup: 'animate__animated animate__zoomIn'
                            },
                            hideClass: {
                                popup: 'animate__animated animate__zoomOut'
                            }
                        }).then(() => {
                            if (closestElem == '.card') {
                                $(this).closest(closestElem).parent().remove()
                            } else {
                                $(this).closest(closestElem).remove()
                            }
                        })
                    } else {
                        swalWithBootstrapButtons.fire({
                            title: "Your Request is safe!",
                            showClass: {
                                popup: 'animate__animated animate__zoomIn'
                            },
                            hideClass: {
                                popup: 'animate__animated animate__zoomOut'
                            }
                        });
                    }
                });
        })


        $('#warning').on('click', function() {
            Swal.fire({
                icon: 'warning',
                title: 'Changes are not saved',
                showConfirmButton: false,

            })
        });

        $('#confirmation').on('click', function() {
            Swal.fire({
                    title: "Are you sure?",
                    text: "Once deleted, you will not be able to recover this imaginary file!",
                    icon: "warning",
                    buttons: true,
                    dangerMode: true,
                })
                .then((willDelete) => {
                    if (willDelete) {
                        Swal.fire("Poof! Your imaginary file has been deleted!", {
                            icon: "success",
                        });
                    } else {
                        Swal.fire("Your imaginary file is safe!");
                    }
                });
        });
        /*----------------
        weather
        ---------------*/
        if(jQuery('#weather-chart').length){
            am4core.ready(function() {

        // Themes begin
        am4core.useTheme(am4themes_animated);
        // Themes end


        // Create map instance
        var chart = am4core.create("weather-chart", am4maps.MapChart);

        // Set map definition
        chart.geodata = am4geodata_worldHigh;

        // Set projection
        chart.projection = new am4maps.projections.Mercator();

        // Center on the groups by default
        chart.homeZoomLevel = 6;
        chart.homeGeoPoint = { longitude: 10, latitude: 51 };

        // Polygon series
        var polygonSeries = chart.series.push(new am4maps.MapPolygonSeries());
        polygonSeries.exclude = ["AQ"];
        polygonSeries.useGeodata = true;
        polygonSeries.nonScalingStroke = true;
        polygonSeries.strokeOpacity = 0.5;

        // Image series
        var imageSeries = chart.series.push(new am4maps.MapImageSeries());
        var imageTemplate = imageSeries.mapImages.template;
        imageTemplate.propertyFields.longitude = "longitude";
        imageTemplate.propertyFields.latitude = "latitude";
        imageTemplate.nonScaling = true;

        var image = imageTemplate.createChild(am4core.Image);
        image.propertyFields.href = "imageURL";
        image.width = 50;
        image.height = 50;
        image.horizontalCenter = "middle";
        image.verticalCenter = "middle";

        var label = imageTemplate.createChild(am4core.Label);
        label.text = "{label}";
        label.horizontalCenter = "middle";
        label.verticalCenter = "top";
        label.dy = 20;

        imageSeries.data = [{
          "latitude": 40.416775,
          "longitude": -3.703790,
          "imageURL": "https://www.amcharts.com/lib/images/weather/animated/rainy-1.svg",
          "width": 32,
          "height": 32,
          "label": "Madrid: +22C"
        }, {
          "latitude": 48.856614,
          "longitude": 2.352222,
          "imageURL": "https://www.amcharts.com/lib/images/weather/animated/thunder.svg",
          "width": 32,
          "height": 32,
          "label": "Paris: +18C"
        }, {
          "latitude": 52.520007,
          "longitude": 13.404954,
          "imageURL": "https://www.amcharts.com/lib/images/weather/animated/cloudy-day-1.svg",
          "width": 32,
          "height": 32,
          "label": "Berlin: +13C"
        }, {
          "latitude": 52.229676,
          "longitude": 21.012229,
          "imageURL": "https://www.amcharts.com/lib/images/weather/animated/day.svg",
          "width": 32,
          "height": 32,
          "label": "Warsaw: +22C"
        }, {
          "latitude": 41.872389,
          "longitude": 12.480180,
          "imageURL": "https://www.amcharts.com/lib/images/weather/animated/day.svg",
          "width": 32,
          "height": 32,
          "label": "Rome: +29C"
        }, {
          "latitude": 51.507351,
          "longitude": -0.127758,
          "imageURL": "https://www.amcharts.com/lib/images/weather/animated/rainy-7.svg",
          "width": 32,
          "height": 32,
          "label": "London: +10C"
        }, {
          "latitude": 59.329323,
          "longitude": 18.068581,
          "imageURL": "https://www.amcharts.com/lib/images/weather/animated/rainy-1.svg",
          "width": 32,
          "height": 32,
          "label": "Stockholm: +8C"
        } ];

        });
        }
/*---------------------------------------------------------------------
Editable Table
-----------------------------------------------------------------------*/
    const $tableID = $("#table"),
        $BTN = $("#export-btn"),
        $EXPORT = $("#export"),
        newTr = '\n<tr class="hide">\n  <td class="pt-3-half" contenteditable="true">Example</td>\n  <td class="pt-3-half" contenteditable="true">Example</td>\n  <td class="pt-3-half" contenteditable="true">Example</td>\n  <td class="pt-3-half" contenteditable="true">Example</td>\n  <td class="pt-3-half" contenteditable="true">Example</td>\n  <td class="pt-3-half">\n    <span class="table-up"><a href="#!" class="indigo-text"><i class="fas fa-long-arrow-alt-up" aria-hidden="true"></i></a></span>\n    <span class="table-down"><a href="#!" class="indigo-text"><i class="fas fa-long-arrow-alt-down" aria-hidden="true"></i></a></span>\n  </td>\n  <td>\n    <span class="table-remove"><button type="button" class="btn btn-danger btn-rounded btn-sm my-0 waves-effect waves-light">Remove</button></span>\n  </td>\n</tr>';
    $(".table-add").on("click", "i", () => {
        const e = $tableID.find("tbody tr").last().clone(!0).removeClass("hide table-line");
        0 === $tableID.find("tbody tr").length && $("tbody").append(newTr), $tableID.find("table").append(e)
    }), $tableID.on("click", ".table-remove", function() {
        $(this).parents("tr").detach()
    }), $tableID.on("click", ".table-up", function() {
        const e = $(this).parents("tr");
        1 !== e.index() && e.prev().before(e.get(0))
    }), $tableID.on("click", ".table-down", function() {
        const e = $(this).parents("tr");
        e.next().after(e.get(0))
    }), jQuery.fn.pop = [].pop, jQuery.fn.shift = [].shift, $BTN.on("click", () => {
        const e = $tableID.find("tr:not(:hidden)"),
            t = [],
            a = [];
        $(e.shift()).find("th:not(:empty)").each(function() {
            t.push($(this).text().toLowerCase())
        }), e.each(function() {
            const e = $(this).find("td"),
                n = {};
            t.forEach((t, a) => {
                n[t] = e.eq(a).text()
            }), a.push(n)
        }), $EXPORT.text(JSON.stringify(a))
    }), $(document).ready(function() {
        var e, t, a, n, o = 1,
            r = $("fieldset").length;

        function i(e) {
            var t = parseFloat(100 / r) * e;
            t = t.toFixed(), $(".progress-bar").css("width", t + "%")
        }
        i(o), $(".next").click(function() {
            e = $(this).parent(), t = $(this).parent().next(), $("#top-tab-list li").eq($("fieldset").index(t)).addClass("active"), $("#top-tab-list li").eq($("fieldset").index(e)).addClass("done"), t.show(), e.animate({
                opacity: 0
            }, {
                step: function(a) {
                    n = 1 - a, e.css({
                        display: "none",
                        position: "relative"
                    }), t.css({
                        opacity: n
                    })
                },
                duration: 500
            }), i(++o)
        }), $(".previous").click(function() {
            e = $(this).parent(), a = $(this).parent().prev(), $("#top-tab-list li").eq($("fieldset").index(e)).removeClass("active"), $("#top-tab-list li").eq($("fieldset").index(a)).removeClass("done"), a.show(), e.animate({
                opacity: 0
            }, {
                step: function(t) {
                    n = 1 - t, e.css({
                        display: "none",
                        position: "relative"
                    }), a.css({
                        opacity: n
                    })
                },
                duration: 500
            }), i(--o)
        }), $(".submit").click(function() {
            return !1
        })
    }), $(document).ready(function() {
        var e = $("div.setup-panel div a"),
            t = $(".setup-content"),
            a = $(".nextBtn");
        t.hide(), e.click(function(a) {
            a.preventDefault();
            var n = $($(this).attr("href")),
                o = $(this);
            o.hasClass("disabled") || (e.addClass("active"), o.parent().addClass("active"), t.hide(), n.show(), n.find("input:eq(0)").focus())
        }), a.click(function() {
            var e = $(this).closest(".setup-content"),
                t = e.attr("id"),
                a = $('div.setup-panel div a[href="#' + t + '"]').parent().next().children("a"),
                n = e.find("input[type='text'],input[type='email'],input[type='password'],input[type='url'],textarea"),
                o = !0;
            $(".form-group").removeClass("has-error");
            for (var r = 0; r < n.length; r++) n[r].validity.valid || (o = !1, $(n[r]).closest(".form-group").addClass("has-error"));
            o && a.removeAttr("disabled").trigger("click")
        }), $("div.setup-panel div a.active").trigger("click")
    }), $(document).ready(function() {
        var e, t, a, n, o = 1,
            r = $("fieldset").length;

        function i(e) {
            var t = parseFloat(100 / r) * e;
            t = t.toFixed(), $(".progress-bar").css("width", t + "%")
        }
        i(o), $(".next").click(function() {
            e = $(this).parent(), t = $(this).parent().next(), $("#top-tabbar-vertical li").eq($("fieldset").index(t)).addClass("active"), t.show(), e.animate({
                opacity: 0
            }, {
                step: function(a) {
                    n = 1 - a, e.css({
                        display: "none",
                        position: "relative"
                    }), t.css({
                        opacity: n
                    })
                },
                duration: 500
            }), i(++o)
        }), $(".previous").click(function() {
            e = $(this).parent(), a = $(this).parent().prev(), $("#top-tabbar-vertical li").eq($("fieldset").index(e)).removeClass("active"), a.show(), e.animate({
                opacity: 0
            }, {
                step: function(t) {
                    n = 1 - t, e.css({
                        display: "none",
                        position: "relative"
                    }), a.css({
                        opacity: n
                    })
                },
                duration: 500
            }), i(--o)
        }), $(".submit").click(function() {
            return !1
        })
    }), $(document).ready(function() {
        $(".file-upload").on("change", function() {
            ! function(e) {
                if (e.files && e.files[0]) {
                    var t = new FileReader;
                    t.onload = function(e) {
                        $(".profile-pic").attr("src", e.target.result)
                    }, t.readAsDataURL(e.files[0])
                }
            }(this)
        }), $(".upload-button").on("click", function() {
            $(".file-upload").click()
        })
    }), $(function() {
        function e(e) {
            return e / 100 * 360
        }
        $(".progress-round").each(function() {
            var t = $(this).attr("data-value"),
                a = $(this).find(".progress-left .progress-bar"),
                n = $(this).find(".progress-right .progress-bar");
            t > 0 && (t <= 50 ? n.css("transform", "rotate(" + e(t) + "deg)") : (n.css("transform", "rotate(180deg)"), a.css("transform", "rotate(" + e(t - 50) + "deg)")))
        })
    });
    });
})(jQuery);

// đặt class cho số lượng hình ảnh được hiển thi
document.addEventListener("DOMContentLoaded", function() {
    const images = document.querySelectorAll('.grid-item');
    const container = document.querySelector('.grid-container');

    const imageCount = images.length;

    if (imageCount === 1) {
        container.classList.add('one-image');
    } else if (imageCount === 2) {
        container.classList.add('two-images');
    } else if (imageCount === 3) {
        container.classList.add('three-images');
    } else if (imageCount === 4) {
        container.classList.add('four-images');
    } else if (imageCount >= 5) {
        container.classList.add('five-images');
    }
});
// hiển thị các bình luận ẩn
document.getElementById('loadMore').addEventListener('click', function() {
    // Lấy tất cả các bình luận ẩn
    const hiddenComments = document.querySelectorAll('#commentList .d-none');

    const commentsToShow = 5;

    for (let i = 0; i < commentsToShow && i < hiddenComments.length; i++) {
        hiddenComments[i].classList.remove('d-none');
    }

    if (hiddenComments.length <= commentsToShow) {
        document.getElementById('loadMoreContainer').style.display = 'none';
    }
});

// sự kiện khi modal ảnh hiển thị
document.addEventListener('DOMContentLoaded', function () {
    const images = [...document.querySelectorAll('.grid-item img')]; // Lấy tất cả hình ảnh
    const modalImage = document.getElementById('modalImage');
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    let currentIndex = 0;

    // Mở modal và hiển thị hình ảnh
    document.querySelectorAll('.grid-item a').forEach((link, index) => {
        link.addEventListener('click', function (event) {
            event.preventDefault();
            currentIndex = index;
            modalImage.src = link.getAttribute('data-bs-image');
            updateButtonVisibility();
            $('#imageModal').modal('show');
        });
    });

    function updateButtonVisibility() {
        prevBtn.style.display = currentIndex > 0 ? 'inline-block' : 'none';
        nextBtn.style.display = currentIndex < images.length - 1 ? 'inline-block' : 'none';
    }

    // Sự kiện cho nút "Quay lại"
    prevBtn.addEventListener('click', function () {
        if (currentIndex > 0) {
            currentIndex--;
            modalImage.src = images[currentIndex].src;
            updateButtonVisibility();
        }
    });

    nextBtn.addEventListener('click', function () {
        if (currentIndex < images.length - 1) {
            currentIndex++;
            modalImage.src = images[currentIndex].src; // Cập nhật hình ảnh trong modal
            updateButtonVisibility();
        }
    });

    $('#imageModal').on('hide.bs.modal', function (e) {
        if (prevBtn.contains(e.target) || nextBtn.contains(e.target)) {
            e.preventDefault();
        }
    });
});


document.addEventListener('DOMContentLoaded', function () {
    const message = /*[[${message}]]*/ '';
    if (message) {
        toastr.success(message, 'Thông báo', {
            "positionClass": "toast-top-right", // Vị trí hiển thị
            "timeOut": "5000", // Thời gian hiển thị
            "closeButton": true // Hiện nút đóng
        });
    }
});

//tăng giảm ảnh
document.addEventListener('DOMContentLoaded', function() {
    const modalImage = document.getElementById('modalImage');
    let currentScale = 1;
    const maxScale = 1.5;
    const minScale = 0.5;

    document.getElementById('zoomInBtn').addEventListener('click', function() {
        if (currentScale < maxScale) {
            currentScale += 0.1;
            modalImage.style.transform = `scale(${currentScale})`;
        }
    });

    document.getElementById('zoomOutBtn').addEventListener('click', function() {
        if (currentScale > minScale) {
            currentScale -= 0.1;
            modalImage.style.transform = `scale(${currentScale})`;
        }
    });

    function resetImageScale() {
        currentScale = 1;
        modalImage.style.transform = `scale(${currentScale})`;
    }

    document.getElementById('nextBtn').addEventListener('click', function() {
        resetImageScale();
    });

    document.getElementById('prevBtn').addEventListener('click', function() {
        resetImageScale();
    });
});

function setMinToDate() {
    const fromDateInput = document.getElementById('fromDate');
    const toDateInput = document.getElementById('toDate');

    const fromDateValue = fromDateInput.value;

    if (fromDateValue) {
        toDateInput.min = fromDateValue;
        if (toDateInput.value < fromDateValue) {
            toDateInput.value = fromDateValue;
        }
    } else {
        toDateInput.min = '';
    }
}

//stastics
$(document).ready(function() {
    // Lấy dữ liệu từ model
    const likesCount = /*[[${likesCount}]]*/ {};
    const commentsCount = /*[[${commentsCount}]]*/ {};
    const newUsersCount = /*[[${newUsersCount}]]*/ {};
    const postsCount = /*[[${postsCount}]]*/ {};

    // In ra console để kiểm tra
    console.log('Likes Count:', likesCount);
    console.log('Comments Count:', commentsCount);
    console.log('New Users Count:', newUsersCount);
    console.log('Posts Count:', postsCount);

    // Tạo biểu đồ cho lượt thích
    const likesCtx = document.getElementById('likesChart').getContext('2d');
    const likesChart = new Chart(likesCtx, {
        type: 'bar',
        data: {
            labels: Object.keys(likesCount).map(month => {
                return month === '1' ? 'Tháng 1' :
                    month === '2' ? 'Tháng 2' :
                        month === '3' ? 'Tháng 3' :
                            month === '4' ? 'Tháng 4' :
                                month === '5' ? 'Tháng 5' :
                                    month === '6' ? 'Tháng 6' :
                                        month === '7' ? 'Tháng 7' :
                                            month === '8' ? 'Tháng 8' :
                                                month === '9' ? 'Tháng 9' :
                                                    month === '10' ? 'Tháng 10' :
                                                        month === '11' ? 'Tháng 11' : 'Tháng 12';
            }),
            datasets: [{
                label: 'Số lượt thích',
                data: Object.values(likesCount),
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    // Tạo biểu đồ cho bình luận
    const commentsCtx = document.getElementById('commentsChart').getContext('2d');
    const commentsChart = new Chart(commentsCtx, {
        type: 'bar',
        data: {
            labels: Object.keys(commentsCount).map(month => {
                return month === '1' ? 'Tháng 1' :
                    month === '2' ? 'Tháng 2' :
                        month === '3' ? 'Tháng 3' :
                            month === '4' ? 'Tháng 4' :
                                month === '5' ? 'Tháng 5' :
                                    month === '6' ? 'Tháng 6' :
                                        month === '7' ? 'Tháng 7' :
                                            month === '8' ? 'Tháng 8' :
                                                month === '9' ? 'Tháng 9' :
                                                    month === '10' ? 'Tháng 10' :
                                                        month === '11' ? 'Tháng 11' : 'Tháng 12';
            }),
            datasets: [{
                label: 'Số bình luận',
                data: Object.values(commentsCount),
                backgroundColor: 'rgba(153, 102, 255, 0.2)',
                borderColor: 'rgba(153, 102, 255, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    // Tạo biểu đồ cho người dùng mới
    const newUsersCtx = document.getElementById('newUsersChart').getContext('2d');
    const newUsersChart = new Chart(newUsersCtx, {
        type: 'bar',
        data: {
            labels: Object.keys(newUsersCount).map(month => {
                return month === '1' ? 'Tháng 1' :
                    month === '2' ? 'Tháng 2' :
                        month === '3' ? 'Tháng 3' :
                            month === '4' ? 'Tháng 4' :
                                month === '5' ? 'Tháng 5' :
                                    month === '6' ? 'Tháng 6' :
                                        month === '7' ? 'Tháng 7' :
                                            month === '8' ? 'Tháng 8' :
                                                month === '9' ? 'Tháng 9' :
                                                    month === '10' ? 'Tháng 10' :
                                                        month === '11' ? 'Tháng 11' : 'Tháng 12';
            }),
            datasets: [{
                label: 'Số người dùng mới',
                data: Object.values(newUsersCount),
                backgroundColor: 'rgba(255, 159, 64, 0.2)',
                borderColor: 'rgba(255, 159, 64, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    // Tạo biểu đồ cho bài đăng
    const postsCtx = document.getElementById('postsChart').getContext('2d');
    const postsChart = new Chart(postsCtx, {
        type: 'bar',
        data: {
            labels: Object.keys(postsCount).map(month => {
                return month === '1' ? 'Tháng 1' :
                    month === '2' ? 'Tháng 2' :
                        month === '3' ? 'Tháng 3' :
                            month === '4' ? 'Tháng 4' :
                                month === '5' ? 'Tháng 5' :
                                    month === '6' ? 'Tháng 6' :
                                        month === '7' ? 'Tháng 7' :
                                            month === '8' ? 'Tháng 8' :
                                                month === '9' ? 'Tháng 9' :
                                                    month === '10' ? 'Tháng 10' :
                                                        month === '11' ? 'Tháng 11' : 'Tháng 12';
            }),
            datasets: [{
                label: 'Số bài đăng',
                data: Object.values(postsCount),
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
});