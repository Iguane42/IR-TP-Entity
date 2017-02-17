<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div  class="col-md-11 col-md-offset-1">
    <h1 align='center'>Catalogue des oeuvres</h1>
    <table class="table table-bordered table-striped">
        <thead>
            <tr>
                <td>Titre</td>
                <td>Prix</td>
                <td>Prénom propriétaire</td>
                <td>Nom propriétaire</td>
                <td>Réserver</td>
                <td>Modifier</td>
                <td>Supprimer</td>                
            </tr>  
        </thead>
        <tbody>
            <c:forEach var="oeuvre" items="${lstOeuvresR}">
                <tr>
                    <td>${oeuvre.titre}</td>
                    <td>${oeuvre.prix}</td>
                    <td>${oeuvre.proprietaire.prenom_proprietaire}</td>
                    <td>${oeuvre.proprietaire.nom_proprietaire}</td>
                    <td><a class="btn btn-primary" href="reserver.res?id=${oeuvre.id_oeuvre}">Réserver</a></td>
                    <td><a class="btn btn-primary" href="modifier.oe?id=${oeuvre.id_oeuvre}">Modifier</a></td> 
                    <td><a class="btn btn-primary" onclick="javascript:if (confirm('Suppression confirmée ?')){ window.location='supprimer.oe?id=${oeuvre.id_oeuvre}';}">Supprimer</a></td>                     
                </tr>
            </c:forEach>
        </tbody>
    </table>          
</div>
