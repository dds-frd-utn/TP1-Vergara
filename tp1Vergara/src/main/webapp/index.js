const impuesto = 1.21
var urlCuenta

function volverAMenuPrincipal(){
  $("#retirar").empty()
  $("#deposito").empty()
  $("#transferencia").empty()
  $("#botonVolver").hide()
  $("#botonera").show("slow")
}

//Login-------------------------------------------------------------------

$("#botonLogin").click( function(){
  var urlApi = "http://localhost:8080/tp1Vergara/rest/cuentas/".concat($("#inputIdCuenta").val())
  var urlCuenta = "http://localhost:8080/tp1Vergara/rest/cuentas/".concat($("#inputIdCuenta").val())
  $.ajax({
    url: urlApi,
    success: function(data){
      //Cliente no existe
      if(typeof(data) == "undefined"){
        $("#login").append($("<span>Error: no existe una cuenta con ese numero</span>"))
      }

      //Cliente existe
      else{
        $("#login").remove()
        mostrarMensajeBienvenida(JSON.stringify(data))
        mostrarBotonera(JSON.stringify(data))  
      }
    }
  })
})


function inputVacio(elem){
  if(typeof(elem) == "number"){
    return elem.toString().length == 0
  }

  return elem.length == 0
}

var botonLogin = document.getElementById('botonLogin')
var inputIdCliente = document.getElementById('inputIdCuenta')
var inputContrasenia = document.getElementById('inputContrasenia')


function botonLoginDeshabilitado(){botonLogin.disabled = inputVacio(inputContrasenia.value) || inputVacio(inputIdCuenta.value)}

//------------------------------------------------------------------------




function mostrarMensajeBienvenida(data){
  var infoCuenta = JSON.parse(data)
  var urlApi = "http://localhost:8080/tp1Vergara/rest/clientes/".concat(infoCuenta.idCliente.toString())

  $.ajax({
    url: urlApi,
    success: function(data){
      var mensajeBienvenida = `
        <h1>Bienvenido/a ${data.nombreCliente} </h1> 
        <h1>Esta operando en la cuenta: ${infoCuenta.idCuenta}</h1>
        <h4>Su saldo es de: ${infoCuenta.saldo}</h4>`
      
      $("#bienvenida").append($(mensajeBienvenida))
    }
  })
}

function mostrarBotonera(data){

  $("#botonera").show("slow")

  $("#botonRetirar").on('click', {data}, gestionRetiro)
  $("#botonDeposito").on('click', {data}, gestionDeposito)
  $("#botonTransferencia").on('click', {data}, gestionTransferencia)
  $("#botonVerUltimosMovimientos").on('click', {data}, verUltimosMovimientos)
}

function gestionRetiro(event){
  $("#botonVolver").show("slow")
  $("#mensajeFinalizoOperacion").empty()
  $("#botonera").hide("slow")
  
  $("#retirar").append(`
    <h2>Gestion de retiro</h2>
    <label>Ingrese cantidad a retirar: <input type="number" id="montoARetirar" /> </label>
    </br>
    <button id="botonRetiro" diasbled>Retirar</button>
    <span id="errorRetiro"></span>
  `)

  $("#botonRetiro").on('click', event.data, function(event){
    var nroCuenta = JSON.parse(event.data.data).idCuenta
    var infoCuenta

    //Obtengo datos actualizados de la cuenta
    $.ajax({
      url: `http://localhost:8080/tp1Vergara/rest/cuentas/${nroCuenta}`,
      type: 'GET',
      contentType: "application/json",
      success: function(data){
        infoCuenta = data

        var montoARetirar = Number($("#montoARetirar").val())
    
        if(montoARetirar>0 && montoARetirar<=infoCuenta.saldo){
          
          urlApi = `http://localhost:8080/tp1Vergara/rest/cuentas/${infoCuenta.idCuenta}`
          var nuevoSaldo = infoCuenta.saldo - montoARetirar 
          
          var jsonAEnviar = `{
            "idCuenta" : ${infoCuenta.idCuenta},
            "saldo": ${nuevoSaldo},
            "idCliente": ${infoCuenta.idCliente},
            "fechaApertura": "${infoCuenta.fechaApertura}"
          }`
    
          $.ajax({
            url: urlApi,
            type: 'PUT',
            contentType: "application/json",
            data: jsonAEnviar,
            success: function(data){
              $("#mensajeFinalizoOperacion").append("<h2>Su retiro fue realizado</h2>")
              $("#bienvenida").empty()
              $("#retirar").empty()
              mostrarMensajeBienvenida(jsonAEnviar)
              $("#botonera").show("slow")
    
              //Agrego a movimientos
              var fechaMovimiento = moment.utc().format()
    
              $.ajax({
                url: "http://localhost:8080/tp1Vergara/rest/movimientos",
                type: 'POST',
                contentType: "application/json",
                data: `{
                  "fecha" : "${fechaMovimiento}",
                  "idCuentaDestino": ${infoCuenta.idCuenta},
                  "idCuentaOrigen": ${infoCuenta.idCuenta},
                  "monto": ${-montoARetirar},
                  "descripcion": "Retiro de efectivo"
                }`,
                success: function(data){console.log("Agregado a transacciones")}
              })
            }
          })
        }
      
        else{
          if(montoARetirar<0){
            $("#errorRetiro").empty()
            $("#errorRetiro").append("<span>El monto a retirar debe ser mayor a 0</span>")
          }
          else{
            $("#errorRetiro").empty()
            $("#errorRetiro").append("<span>No dispone del dinero suficiente en esta cuenta</span>")
          }
        }
      }
    })


  })
}
  

function gestionDeposito(event){
  $("#botonVolver").show("slow")
  $("#mensajeFinalizoOperacion").empty()
  $("#botonera").hide("slow")
  
  $("#deposito").append(`
    <h2Gestion de deposito</h2>
    <label>Ingrese cantidad a depositar: <input type="number" id="montoADepositar" /> </label>
    </br>
    <button id="botonDepositar">Depositar</button>
  `)

  $("#botonDepositar").on('click', event.data, function(event){
    var nroCuenta = JSON.parse(event.data.data).idCuenta
    var infoCuenta
    
    //Obtengo datos actualizados de la cuenta
    $.ajax({
      url: `http://localhost:8080/tp1Vergara/rest/cuentas/${nroCuenta}`,
      type: 'GET',
      contentType: "application/json",
      success: function(data){
        infoCuenta = data
    var montoADepositar = Number($("#montoADepositar").val())
    
    if(montoADepositar>0){
      urlApi = `http://localhost:8080/tp1Vergara/rest/cuentas/${infoCuenta.idCuenta}`
      var nuevoSaldo = infoCuenta.saldo + montoADepositar 
    
      var jsonAEnviar = `{
        "idCuenta" : ${infoCuenta.idCuenta},
        "saldo": ${nuevoSaldo},
        "idCliente": ${infoCuenta.idCliente},
        "fechaApertura": "${infoCuenta.fechaApertura}"
      }`

      $.ajax({
        url: urlApi,
        type: 'PUT',
        contentType: "application/json",
        data: jsonAEnviar,
        success: function(data){
          $("#mensajeFinalizoOperacion").append("<h2>Su deposito fue realizado</h2>")
          $("#bienvenida").empty()
          $("#deposito").empty()
          mostrarMensajeBienvenida(jsonAEnviar)
          $("#botonera").show("slow")

          //Agrego a movimientos
          var fechaMovimiento = moment.utc().format()

          $.ajax({
            url: "http://localhost:8080/tp1Vergara/rest/movimientos",
            type: 'POST',
            contentType: "application/json",
            data: `{
              "fecha" : "${fechaMovimiento}",
              "idCuentaDestino": ${infoCuenta.idCuenta},
              "idCuentaOrigen": ${infoCuenta.idCuenta},
              "monto": ${montoADepositar},
              "descripcion": "Retiro de efectivo"
            }`,
            success: function(data){console.log("Agregado a transacciones")}
          })
        }
      })
    }
  }
    })
  })
}





function gestionTransferencia(event){
  $("#botonVolver").show()
  $("#mensajeFinalizoOperacion").empty()
  $("#botonera").hide("slow")

  var pantallaTransferencia = `
    <h2>Gestion de transferencia</h2>
    <label>Ingrese la cuenta a la cual desea transferir: <input id="cuentaDestino" type="number"> </label> 
    </br>
    <label>Ingrese el monto a transferir: <input id="montoTransferencia" type="number"> </label> 
    </br>
    <label>Ingrese descripcion del movimiento: <input id="descripcionMovimiento" type="text"> </label>
    <button id="submitTransferencia">Realizar transferencia</button>
    <span id="errorTransferencia"></span>`
  
  $("#transferencia").append($(pantallaTransferencia))

  $("#submitTransferencia").on('click', event.data, realizarTransferencia)
}


function realizarTransferencia(event){
  var infoCuentaOrigen = JSON.parse(event.data.data)
  var cuentaDestino = parseInt($("#cuentaDestino").val(), 10)
  var montoTransferencia = parseInt($("#montoTransferencia").val(), 10)
  var descripcionMovimiento = $("#descripcionMovimiento").val()

  if((montoTransferencia*impuesto <= infoCuentaOrigen.saldo) && (montoTransferencia > 0)){
    var urlApi = `http://localhost:8080/tp1Vergara/rest/cuentas/${cuentaDestino}`
    
    //Busco informacion de la cuenta destino
    $.ajax({
      url: urlApi,
      type : 'GET',
      success: function(data){
        console.log("funciona GET")
        if(typeof(data) == "undefined"){
          //No existe la cuenta destino
          $("#errorTransferencia").empty()
          $("#errorTransferencia").append("<span> No existe la cuenta destino. Ingrese otra cuenta.</span>")
        }

        else{
          //Existe cuenta destino
          var nuevoSaldoDestino = data.saldo + montoTransferencia

          $.ajax({
            url: urlApi,
            data: `{
              "idCuenta": ${data.idCuenta}, 
              "saldo" : ${nuevoSaldoDestino}, 
              "idCliente" : ${data.idCliente}, 
              "fechaApertura" : "${data.fechaApertura}"
            }`,
            type: 'PUT',
            contentType: 'application/json',
            success: function(data) {

              console.log("Funciona PUT destino")
              var nuevoSaldoOrigen = infoCuentaOrigen.saldo - montoTransferencia*impuesto
              urlApi = `http://localhost:8080/tp1Vergara/rest/cuentas/${infoCuentaOrigen.idCuenta}`

              $.ajax({
                url: urlApi,
                type: 'PUT',
                data: `{
                  "idCuenta" : ${infoCuentaOrigen.idCuenta}, 
                  "saldo":${nuevoSaldoOrigen}, 
                  "idCliente":${infoCuentaOrigen.idCliente}, 
                  "fechaApertura": "${infoCuentaOrigen.fechaApertura}"
                }`,
                contentType: 'application/json',
                success: function(data){
                  console.log("funciona PUT origen")
                  var timeStamp = moment.utc().format()

                  $.ajax({
                    url: "http://localhost:8080/tp1Vergara/rest/movimientos",
                    type: 'POST',
                    data: `{
                      "fecha" : "${timeStamp}", 
                      "monto": ${montoTransferencia}, 
                      "idCuentaOrigen": ${infoCuentaOrigen.idCuenta}, 
                      "idCuentaDestino": ${cuentaDestino}, 
                      "descripcion": ${descripcionMovimiento}
                    }`,
                    contentType: "application/json",
                    success: function(data){
                      console.log("Funciona POST movimientos")
                      $("#mensajeFinalizoOperacion").append("<h2>Su transferencia fue realizada</h2>")
                      $("#bienvenida").empty()
                      $("#deposito").empty()
                      mostrarMensajeBienvenida(infoCuentaOrigen)
                      $("#botonera").show()
                    }
                  })
                },
              })
            },
          })

          
      }
    }})
  }

  else{
    if(montoTransferencia < 0){
      $("#errorTransferencia").empty()
      $("#errorTransferencia").append(`<span id="errorMontoNegativo"> El monto a transferir debe ser  mayor a cero.</span>`)
      
    }
    else{
      $("#errorTransferencia").empty()
      $("#errorTransferencia").append("<span> No tiene saldo suficiente como para realizar esta transferencia.</span>")
    }
  }
}


function verUltimosMovimientos(){
  alert("Ultimos movimientos")
}