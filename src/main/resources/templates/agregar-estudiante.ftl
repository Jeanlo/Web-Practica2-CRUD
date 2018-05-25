<#import "/templates/base.ftl" as base>
<@base.page>
<div class="col-lg-6 col-md-8 col-sm-10 mb-3 mx-auto">
    <div class="card bg-light rounded-0">
        <div class="card-body">
            <h5 class="card-title m-0">
                <i class="fas fa-user-plus"></i> Agregar estudiante
            </h5>
        </div>
        <form action="/agregar-estudiante" method="post" class="p-2">
            <div class="form-group">
                <label for="matricula">Matricula</label>
                <input type="text" class="form-control rounded-0" name="matricula" placeholder="matricula">
            </div>
            <div class="row">
                <div class="col-6">
                    <div class="form-group">
                        <label for="nombre">Nombre</label>
                        <input type="text" class="form-control rounded-0" name="nombre" placeholder="nombre">
                    </div>
                </div>
                <div class="col-6">
                    <div class="form-group">
                        <label for="apellido">Apellido</label>
                        <input type="text" class="form-control rounded-0" name="apellido" placeholder="apellido">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="telefono">Teléfono</label>
                <input type="text" class="form-control rounded-0" name="telefono" placeholder="telefono">
            </div>
            <button type="submit" class="btn btn-outline-secondary rounded-0 float-right">
                <i class="fas fa-plus"></i> Agregar
            </button>
        </form>
    </div>
</div>
</@base.page>