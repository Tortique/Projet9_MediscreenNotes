<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:th="http:/www.thymeleaf.org">

<head>
    <title>Abernathy Clinic</title>
</head>

<body>
<div class="container">
    <div class="row" style="margin-left: 40%">
        <div
                class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
            <h1>Abernathy Clinic - Mediscreen</h1>
            <h2>Add a new patient's note</h2>
        </div>
    </div>
    <div class="row" style="margin-left: 20%">
        <div class="form-group">
            <div>
                <a href="../home">Home</a>
            </div>
        </div>
        <div>
            <form th:action="/notes/add" modelattribute="note" method="post" style="width: auto">
                <div>
                    <label>
                        <input type="uuid" name="uuid" class="form-control" placeholder="UUID" required/>
                    </label>
                </div>
                <div>
                    <label>
                        <input type="notes" name="notes" class="form-control" placeholder="Notes" required/>
                    </label>
                </div>
                <div>
                    <button class="btn btn-primary btn-block" type="submit">Add Patient</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>