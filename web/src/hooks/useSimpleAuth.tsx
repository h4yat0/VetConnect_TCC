import { useSelector } from "react-redux";
import { getId } from "../redux/client";

const useSimpleAuth = () => {
  const id = useSelector(getId);
  const loggedIn = id != -1 ? true : false;

  console.log(id)
  console.log(loggedIn)

  return loggedIn;
};

export default useSimpleAuth;
