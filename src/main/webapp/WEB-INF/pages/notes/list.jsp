<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <title>Abernathy Clinic</title>
</head>

<body>
<div class="container">
    <div class="row" style="margin-left: 40%">
        <div
                class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
            <h1>Abernathy Clinic - Mediscreen</h1>
            <h2>Patient's Notes</h2>
        </div>
    </div>
    <div class="row" style="margin-left: 20%">
        <div class="form-group">
            <div>
                <a href="../home">Home</a>
            </div>
        </div>
        <div>
            <table class="table table-striped table-bordered" style="width: 100%" id="patientTable">
                <thead>
                <tr>
                    <th>Uuid</th>
                    <th>Notes</th>
                </tr>
                </thead>
                <tbody style="width: 100%">
                <c:forEach items="${notes}" var="note">
                    <tr>
                        <td>${note.uuid}</td>
                        <td>${note.notes}</td>
                        <td><a href="/notes/edit/${note.id}" >Edit</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br/><a href="add">Add New Patient's notes</a>
        </div>
    </div>
</div>
</body>
</html>