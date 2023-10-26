import { useEffect, useState } from "react";
import useRefreshToken from "../../hooks/useRefreshToken";
import { Outlet } from "react-router-dom";
import { useSelector } from "react-redux";
import { getAccessToken } from "../../redux/client";

const PersistLogin = () => {
  const accessToken = useSelector(getAccessToken);
  const [isloading, setIsLoading] = useState(true);
  const refresh = useRefreshToken();

  useEffect(()=> {}, [accessToken])

  useEffect(() => {
    const verifyRefreshToken = async () => {
      try {
        console.log('aaaaaaaaaaaaaaaaaaaaaaaaa')
        await refresh();
      } catch (err) {
        console.error(err);
      } finally {
        setIsLoading(false);
      }
    };

    !accessToken ? verifyRefreshToken() : setIsLoading(false);
  }, []);

  useEffect(() => {
    
    // console.log(`isLoading: ${isloading}`);
    // console.log(`aT: ${accessToken}`);
  }, [isloading]);

  return <>{isloading ? <p>Loading...</p> : <Outlet />}</>;
};

export default PersistLogin;
