<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
  function onLoad() {
    $("#password").keyup(checkPasswordMatch);
    $("#confirmpass").keyup(checkPasswordMatch);
    $("#details").submit(cansubmit);

  }

  function cansubmit(){
    var password = $("#password").val();
    var confirmpass = $("#confirmpass").val();
    if (password == confirmpass){
      return true;
    } else {
      alert("passwords don't match")
      return false;
    }
  }

  function checkPasswordMatch() {
    var password = $("#password").val();
    var confirmpass = $("#confirmpass").val();
    if (password.length>3 && confirmpass.length>3){

      if (password == confirmpass){
        $("#matchpass").text("Password match");
        $("#matchpass").addClass("valid");
        $("#matchpass").removeClass("error");
      } else
      {
        $("#matchpass").text("Password do not match");
        $("#matchpass").addClass("error");
        $("#matchpass").removeClass("valid");
      }

    }
  }

  $(document).ready(onLoad);
</script>