import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import axios from "../api/axios"
import { getAccessToken, getEmail, updateAccessToken } from "../redux/client";

const useRefreshToken = () => {
  const email = useSelector(getEmail);
  const accessToken = useSelector(getAccessToken);
  const dispatch = useDispatch();

  useEffect(()=>{},[email, accessToken])

  const refresh = async () => {
    console.log(accessToken);
    const response = await axios.put(`/auth/refresh/${email}`, {
      withCredentials: true,
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
    });
    // console.log(useSelector(getAccessToken));
    // console.log(response.data.accessToken);
    dispatch(updateAccessToken(response.data.accessToken));
    return response.data.accessToken;
  }

  return refresh
}

export default useRefreshToken
