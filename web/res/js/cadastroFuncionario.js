
window.onload = function () {


    $(document).ready(function () {
        $('#cpf').mask('999.999.999-99');
        $('#telefone').mask('(99) 9999-9999');
        $('#celular').mask('(99) 99999-9999');
        //$('#cpf').mask('999.999.999-99');
        $('#cep').mask('99999-999');

    });



    document.getElementById("cpf").onkeypress = function () {
        return Onlynumbers(event);
    };

     document.getElementById("telefone").onkeypress = function () {
        return Onlynumbers(event);
    };

     document.getElementById("celular").onkeypress = function () {
        return Onlynumbers(event);
    };

        document.getElementById("cep").onkeypress = function () {
        return Onlynumbers(event);
    };

      document.getElementById("numero").onkeypress = function () {
        return Onlynumbers(event);
    };

       document.getElementById("banco").onkeypress = function () {
        return Onlynumbers(event);
    };

       document.getElementById("agencia").onkeypress = function () {
        return Onlynumbers(event);
    };

       document.getElementById("conta").onkeypress = function () {
        return Onlynumbers(event);
    };

         document.getElementById("digito").onkeypress = function () {
        return Onlynumbers(event);
    };




     document.getElementById("nome").onkeypress = function () {
        return Onlychars(event);
    };

       document.getElementById("logradouro").onkeypress = function () {
        return Onlychars(event);
    };

       document.getElementById("bairro").onkeypress = function () {
        return Onlychars(event);
    };

      document.getElementById("estado").onkeypress = function () {
        return Onlychars(event);
    };


    document.getElementById("cidade").onkeypress = function () {
        return Onlychars(event);
    };









};

function Onlynumbers(e) {
    var tecla = new Number();
    if (window.event) {
        tecla = e.keyCode;
    } else if (e.which) {
        tecla = e.which;
    } else {
        return true;
    }
    if ((tecla >= "33") && (tecla <= "47") || (tecla >= "58") && (tecla <= "126")) {
        return false;
    }
}

function Onlychars(e) {
    var tecla = new Number();
    if (window.event) {
        tecla = e.keyCode;
    } else if (e.which) {
        tecla = e.which;
    } else {
        return true;
    }
    if ((tecla >= "33") && (tecla <= "64")){
        return false;
    }
}
