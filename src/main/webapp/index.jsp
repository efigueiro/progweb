<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-br">
    
    <%@ include file="layout/header.jsp" %>

    <body class="bg-white">
        <!--Navbar-->
        <nav class="navbar navbar-expand-sm bg-white shadow p-2 mb-5">
            <div class="container-fluid">
            	<a class="navbar-brand p-2 text-black fs-3" href="#">Programação Web - Anhanguera</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#topMenu" aria-controls="topMenu" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="topMenu">
                    <ul class="navbar-nav me-auto mb-2 mb-sm-0 lead">
                    	<li class="nav-item p-3">
                            <a class="nav-link" href="#"><strong></strong></a>
                        </li>
                        
                        <li class="nav-item p-3">
                            <a class="nav-link" href="#"><strong></strong></a>
                        </li>
                        
                        <li class="nav-item p-3">
                            <a class="nav-link" href="#"><strong></strong></a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!--End Navbar-->

        <!-- Content -->
        <div class="container">
            <div class="row">
                <div class="container mt-5">
			        <h2 class="mb-4">Descubra Seu Signo</h2>
			        <form id="signoForm" method="post" action="/progweb/signoController">
			            <div class="mb-3">
			                <label for="dataNascimento" class="form-label">Data de Nascimento</label>
			                <input type="text" class="form-control" id="dataNascimento" name="dataNascimento" placeholder="dd/mm/aaaa" required>
			            </div>
			            <button type="submit" class="btn btn-primary">Descobrir</button>
			        </form>
    			</div>
            </div>
        </div>
        <!--End Content-->

        <!--Footer-->
        <footer class="p5 mt-4 text-secondary text-center position-relative">
            <p class="">Copyright &copy; 2024 Programação Web</p>
        </footer>
        <!--End Footer-->

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
        <script src="<%=request.getContextPath()%>/assets/js/script.js"></script>
    </body>
</html>