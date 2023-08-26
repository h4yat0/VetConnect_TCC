import { useLocation, Navigate, Outlet, useResolvedPath } from "react-router-dom";
import { useSelector } from "react-redux";
import { getRoles, getName } from "../redux/client";

interface RequireAuthorizationProps {
  allowedRoles: number[];
}

const RequireAuthorization = (props: RequireAuthorizationProps) => {
  const userName = useSelector(getName);
  const userRoles = useSelector(getRoles);
  const location = useLocation();

  const isAuthorized = userRoles.some((role) => props.allowedRoles.includes(role));

  console.log(userName, isAuthorized, userName ? true : false);

  return isAuthorized ? (
    <Outlet />
  ) : userName ? (
    <Navigate to="/unauthorized" state={{ from: location }} replace />
  ) : (
    <Navigate to="/signin" state={{ from: location }} replace />
  );
};

export default RequireAuthorization;
