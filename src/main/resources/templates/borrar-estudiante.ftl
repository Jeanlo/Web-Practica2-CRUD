<#import "/templates/base.ftl" as base>
<@base.page>
<div class="col-lg-6 col-md-8 col-sm-10 mb-3 mx-auto">
    <div class="card bg-light rounded-0">
        <div class="card-body">
            <h5 class="card-title m-0">
                <i class="fas fa-user-slash"></i> Borrar estudiante
            </h5>
        </div>
        <form action="/estudiante/borrar" method="post" class="p-2">
            <h6>¿Esta seguro de borrar al estudiante ${estudiante.nombre} ${estudiante.apellido} (${estudiante.matricula?string["0"]})?</h6>
            <button type="submit" class="btn btn-outline-secondary rounded-0 float-right">
                <i class="fas fa-trash-alt"></i> Borrar
            </button>
        </form>
    </div>
</div>
</@base.page>