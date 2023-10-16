import { useSelector } from "react-redux";
import { getAccessToken, getId } from "../redux/client";

const useSimpleAuth = () => {
  const accessToken = useSelector(getId);
  const loggedIn = accessToken ? true : false;

  return loggedIn;
};

export default useSimpleAuth;
