/**
 * Created by Ian on 2016/11/29.
 */

var distinguishedChart = new Chart(document.getElementById("distinguished-chart").getContext("2d"));
var problemChart = new Chart(document.getElementById("problem-chart").getContext("2d"));
function search(){
    refreshDistinguishedChart();
    refreshProblemChart();
}

function refreshDistinguishedChart(){
//    var distinguished = JSON.parse($.ajax({url: "distinguished", async: false}).responseText );
	var distinguished = JSON.parse('{"data":{"datasets":{"fillColor":"gba(133,34,25,.8)","data":[10,40],"strokeColor":"rgba(20,20,20,1)"},"labels":["fengxian2","风险一"]},"rid":[2,1]}');
    var data = {
        labels : ["风险1","风险2","3","33","42","sdfa","ddd"],
        datasets : [
            {
                fillColor : "rgba(133,34,25,.8)",
                strokeColor : "rgba(20,20,20,1)",
                data : [65,59,90,81,56,55,40]
            },
            {
                fillColor : "rgba(151,187,205,.8)",
                strokeColor : "rgba(166,166,166,1)",
                data : [28,48,40,19,96,27,100]
            }
        ]
    };

    data = distinguished.data;
    distinguishedChart.Bar(data, null);
}

function refreshProblemChart(){
    var problem = JSON.parse($.ajax({url: "problem", async: false}).responseText );

    var data = {
        labels : ["风险1","风险2","3","33","42","sdfa","ddd"],
        datasets : [
            {
                fillColor : "rgba(133,34,25,.8)",
                strokeColor : "rgba(20,20,20,1)",
                data : [65,59,90,81,56,55,40]
            },
            {
                fillColor : "rgba(151,187,205,.8)",
                strokeColor : "rgba(166,166,166,1)",
                data : [28,48,40,19,96,27,100]
            }
        ]
    };
    data = problem.data;
    problemChart.Bar(data, null);
}

$(document).ready(refreshDistinguishedChart());$(document).ready(refreshProblemChart());