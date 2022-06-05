function calcPlus(where, merchIds, count) {
    var quantity;
    var id;
    sum = 0;
      for(var i = 0; i < count; i++){
        id = parseInt(merchIds[i]);
        quantity = parseInt(document.getElementById("quantity" + id).value);
        if(where == i && document.getElementById('purchaseFlag' + id).checked && quantity < 10){
          var price = document.getElementById('sum' + id).textContent = parseInt(document.getElementById("price" + id).textContent) * (quantity + 1);
          sum += price;
        } else if(document.getElementById('purchaseFlag' + id).checked){
          var price = document.getElementById('sum' + id).textContent = parseInt(document.getElementById("price" + id).textContent) * quantity;
          sum += price;
        }
      }
    var newsum = document.getElementById('sumAll').textContent = sum;
  }

  function calcMinus(where, merchIds, count) {
    var quantity;
    var id;
    sum = 0;
      for(var i = 0; i < count; i++){
        id = parseInt(merchIds[i]);
        quantity = parseInt(document.getElementById("quantity" + id).value);
        if(where == i && document.getElementById('purchaseFlag' + id).checked && quantity > 1){
          var price = document.getElementById('sum' + id).textContent = parseInt(document.getElementById("price" + id).textContent) * (quantity - 1);
          sum += price;
        } else if(document.getElementById('purchaseFlag' + id).checked){
          var price = document.getElementById('sum' + id).textContent = parseInt(document.getElementById("price" + id).textContent) * quantity;
          sum += price;
        }
      }
    var newsum = document.getElementById('sumAll').textContent = sum;
  }
  function calc(merchIds, count) {
    var id;
    sum = 0;
      for(var i = 0; i < count; i++){
        id = parseInt(merchIds[i]);
        quantity = parseInt(document.getElementById("quantity" + id).value);
        if(document.getElementById('purchaseFlag' + id).checked){
          var price = document.getElementById('sum' + id).textContent = parseInt(document.getElementById("price" + id).textContent) * quantity;
          sum += price;
        }
      }
    var newsum = document.getElementById('sumAll').textContent = sum;
  }