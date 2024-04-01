function notificacion(titulo, mensaje, tipo) {
    Swal.fire({
        title: titulo,
        text: mensaje,
        icon: tipo,
        confirmButtonText: 'OK',
        customClass: {
            confirmButton: 'btn btn-primary' 
        }
    });
}

function confirmarEliminarCuenta() {
    var respuesta = prompt('Para confirmar la eliminación de la cuenta, ingresa "si":');
    var numeroCuentaCuentaEliminar = document.getElementById('numeroCuentaCuentaEliminar').value;
    
    if (respuesta.toLowerCase() === 'si') { // Usamos toLowerCase para que acepte 'si' en minúsculas o mayúsculas
        Swal.fire({
            title: '¿Estás seguro?',
            text: 'Estás a punto de eliminar la cuenta con el número ' + numeroCuentaCuentaEliminar,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sí, eliminar'
        }).then((result) => {
            if (result.isConfirmed) {
                // Si el usuario confirma, envía el formulario para eliminar la cuenta
                document.getElementById('eliminarCuentaForm').submit();
            }
        });
    } else {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Respuesta incorrecta. Debes ingresar "si" para confirmar la eliminación de la cuenta.'
        });
    }
}


