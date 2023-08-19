import { useRouteError } from "react-router-dom";

import ButtonPrimary from "../components/buttons/ButtonPrimary";

export default function ErrorPage() {
	const error = useRouteError();
	console.error(error);

	return (
    <main className="grid min-h-screen place-items-center font-inter bg-vetConnectWhite px-6 sm:py-32 lg:px-8">
      <div className="text-center">
        <p className="text-2xl font-semibold text-vetConnectPrimaryGreen">404</p>
        <h1 className="mt-4 text-3xl font-bold tracking-tight text-gray-900 sm:text-5xl">
          Página não encontrada
        </h1>
        <p className="mt-6 text-base leading-7 text-gray-600">
          Desculpe, não conseguimos encontrar a página que você procura.
        </p>
        <div className="mt-10 flex items-center justify-center gap-x-6">
          <a href="/">
            <ButtonPrimary text="Voltar para página principal" />
          </a>
          <a href="#" className="text-sm font-semibold text-gray-900">
            Contact support <span aria-hidden="true">&rarr;</span>
          </a>
        </div>
      </div>
    </main>
  );
}
