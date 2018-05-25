<#import "/templates/base.ftl" as base>
<@base.page>
<div class="col-lg-6 col-md-8 col-sm-10 mb-3 mx-auto">
    <div class="card bg-light rounded-0">
        <div class="card-body">
            <h5 class="card-title m-0">
                <i class="fas fa-user"></i> Estudiante: ${estudiante.nombre} ${estudiante.apellido}
            </h5>
        </div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item"><b>Matrícula</b> ${estudiante.matricula?string["0"]}</li>
            <li class="list-group-item"><b>Nombre</b> ${estudiante.nombre}</li>
            <li class="list-group-item"><b>Apellido</b> ${estudiante.apellido}</li>
            <li class="list-group-item"><b>Telefono</b> ${estudiante.telefono}</li>
        </ul>
    </div>
</div>
</@base.page>