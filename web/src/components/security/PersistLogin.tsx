import { useEffect, useState } from "react";
import { Outlet, useLocation, useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import useRefreshToken from "../../hooks/useRefreshToken";
import {
  getAccessToken,
  getId,
  updateAnimals,
  updateBairro,
  updateBirthDate,
  updateCep,
  updateCity,
  updateComplemento,
  updateCpf,
  updateEmail,
  updateEstado,
  updateId,
  updateName,
  updatePassword,
  updatePhone,
  updateStreetNumber,
  updatestreetName,
} from "../../redux/client";
import api from "../../api/axios";
import jwt from "jwt-decode";

interface jwtDecoded {
  email: string;
}

const KEEP_LOGIN = "/api/cliente/v1/busca-por-email/";
const ANIMALS_URL = "/api/animal/v1/buscar/";

const PersistLogin = () => {
  const dispatch = useDispatch();

  const navigate = useNavigate();
  const location = useLocation();
  const from = location.state?.from?.pathname || "/";

  const refresh = useRefreshToken();
  const accessToken = useSelector(getAccessToken);
  const id = useSelector(getId);

  const [isloading, setIsLoading] = useState(true);

  useEffect(() => {
    const verifyRefreshToken = async () => {
      try {
        await refresh();

      } catch (err) {
        console.error(err);
      } finally {
        setIsLoading(false);
      }
    };

    !accessToken ? verifyRefreshToken() : setIsLoading(false);
  }, []);

  useEffect(()=> {
if (accessToken !== undefined && accessToken !== '') {
  keepLogin()
}

  },[accessToken])

  useEffect(() => {
    if (id > 0) {
      getAnimals();
    }
  }, [id]);

  const keepLogin = async () => {
    if (accessToken !== "" && accessToken === undefined) {
      return;
    }
    
    const accessDecoded: jwtDecoded = jwt(accessToken);
    const email = accessDecoded.email;
    let response = await api
      .get(
        `${KEEP_LOGIN + email}`,
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }
      )
      .then(function (response) {
        let data = response.data;

        dispatch(updateId(data.id));
        dispatch(updateName(data.nome));
        dispatch(updateCpf(data.cpf));
        dispatch(updateEmail(data.email));
        dispatch(updateBirthDate(data.dataNascimento));

        dispatch(updatestreetName(data.rua));
        dispatch(updateBairro(data.bairro));
        dispatch(updateCity(data.cidade));
        dispatch(updateEstado(data.estado));
        dispatch(updateComplemento(data.complemento));
        dispatch(updateStreetNumber(data.numero));
        dispatch(updateCep(data.cep));

        dispatch(updatePhone(data.telefone));
        // dispatch(updatePassword(data.senha));

        navigate(from, { replace: true });
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  const getAnimals = async () => {
    let response = await api
      .get(ANIMALS_URL + id, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      })
      .then(function (response) {
        let data = response.data;
        dispatch(updateAnimals(data));
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  return <>{isloading ? <p>Loading...</p> : <Outlet />}</>;
};

export default PersistLogin;
