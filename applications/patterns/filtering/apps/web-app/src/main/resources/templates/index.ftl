<html>
    <head>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="styles/styles.css">


        <script src="/webjars/sockjs-client/sockjs.min.js"></script>
        <script src="/webjars/stomp-websocket/stomp.min.js"></script>
        <script src="/webjars/jquery/jquery.min.js"></script>
        <script>
                   //Web Sockets
        var socket = new SockJS('/gs-guide-websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        var userName = "nyla";

        stompClient.subscribe('/topic/customerPromotions/'+userName, function (promotionResponse) {

                var promotion = JSON.parse(promotionResponse.body);
                addPromotion(promotion);
            });
    });

    function urlify(text) {
        var urlRegex = /(https?:\/\/[^\s]+)/g;
        return text.replace(urlRegex, function(url) {
        return '<a href="' + url + '">' + url + '</a>';
        })
    }

    function addPromotion(promotion)
    {
       var promotionsPanel = document.getElementById("promotionsPanel");

       if(promotion == null || promotion.products.length == 0)
        {
            promotionsPanel.style = "display: none";
            return;
        }
        else
        {
            promotionsPanel.style = "display: block";
        }

        var promotionContent = document.getElementById("promotions");
        var promotionHTML = "<p>Based on your previous order, you may also be interested in the following:</p>";
         var product = {};

         promotionHTML += "<table id='dataRows'>";
         promotionHTML += "<tr><th>Product</th></tr>";

         for (let x in promotion.products) {
            product = promotion.products[x];

            promotionHTML +="<tr><td>"+product.name+"</td></tr>";
         }

         promotionHTML += "</table>";

         promotionContent.innerHTML = promotionHTML;

        }
        </script>

        <script>
            $(document).ready(function(){
              $("button").click(function(){
                //clear panels
                $("#productsPanel").html("");
                var productName = $("#productSearch").val();
                var urlForProductContainsSearch = "products/product/name/"+productName;
                $.get(urlForProductContainsSearch, function(products, status){

                    var tableHTML = "<table class='dataRows'><tr><th>Id</th><th>Name</th></tr>";
                    for (let x in products) {
                        product = products[x];

                        tableHTML +="<tr>"+"<td>"+product.id+"</td>"+"<td>"+product.name+"</td>"+"</tr>";
                     }
                     tableHTML +="</table>";

                    $("#productsPanel").html(tableHTML);
                });
              });
            });
        </script>
    </head>

    <body>

    <h1> Filtering Data Pattern Demo</h1>
    <h2>${accountType} Accounts</h2>
        <ul>
            <li><a href="/swagger-ui.html">Swagger-UI</a></li>
        </ul>

        <div id="accountsPanel" style="display: none">
            <hr/>
            <h2>Accounts</h2>
            <div id="dataRows">
                <table >
                    <tr>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Status</th>
                        <th>Notes</th>
                        <th>Location</th>
                    </tr>
                    <tbody id="accounts">
                    </tbody>
                </table>
            </div>
        </div>

    <script>

    $(document).ready(
    function() {
        var sse = new EventSource('account/accounts');

        sse.onmessage = function(message) {

        console.log("data: "+message.data);
       	var accountList= JSON.parse(message.data);
       	var tableContent = document.getElementById("accounts");
       	tableContent.innerHTML = ""; //TODO: should optimize this to not redraw everytime

        var productQuantity;

        if(accountList != null && accountList.length > 0 )
        {
           //show panel
           var accountsPanel = document.getElementById("accountsPanel");
           accountsPanel.style  = "display: block";
        }


        for (let x in accountList) {
           account = accountList[x];

            if(tableContent.innerHTML.indexOf(account.name) > -1)
        	    continue; //already exist

            $('#accounts')
            .prepend('<tr>'+
                        '<td>'+ account.name + '</td>'+
                        '<td>'+ account.accountType + '</td>'+
                        '<td>'+ account.status + '</td>'+
                        '<td>'+ account.notes + '</td>'+
                        '<td>'+
                            account.location.address + ' '+
                            account.location.cityTown + ' '+
                            account.location.stateProvince
                           +'</td>'+
                    '</tr>');
        }


       }
   }
);
</script>
    </body>
</html>