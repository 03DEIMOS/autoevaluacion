<%-- 
    Document   : reporteCuantitativo
    Created on : 28/06/2022, 06:08:25 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function () {
        $(document).ready(function () {
            var chart2 = new Highcharts.Chart({
                chart: {
                    renderTo: 'torta',
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false

                },
                title: {
                    text: null
                },
                subtitle: {
                    text: 'Reporte cuantitativo de gráficos por tipos de acción'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            color: '#000000',
                            connectorColor: '#000000',
                            formatter: function () {
                                var igv = this.percentage;
                                igv = igv.toFixed(2);
                                return '<b>' + this.point.name + '</b>: ' + igv + ' %';
                            }
                        }
                    }
                },
                tooltip: {
                    formatter: function () {
                        return '' +
                                this.point.name + ': ' + this.y;
                    }
                },
                series: [{
                        type: 'pie',
                        name: 'Estado',
                        data: [
                            ['Abierta', ${abierta}],
                            ['Cerrada', ${cerrada}],
                            ['Permanente', ${permanente}]
                        ]
                    }]


            });
        })
    });
</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <legend>Reporte cuantitativo de gráficos por tipos de acción (Tipo Chart)</legend>
            <div id="torta" style="height: 500px; margin: 0 auto" class="span10"></div>
        </div>    
    </div>    
</div>
