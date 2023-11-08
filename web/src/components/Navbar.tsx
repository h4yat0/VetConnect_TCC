import ButtonPrimary from "./buttons/ButtonPrimary";
import { useSelector } from "react-redux";
import { getId } from "../redux/client";
import { Fragment, useEffect, useState } from "react";
import { Dialog, Disclosure, Popover, Transition } from "@headlessui/react";

//Icons
import { TbVaccine } from "react-icons/tb";
import { MdPets } from "react-icons/md";
import { FaShower } from "react-icons/fa";
import { IoCutSharp } from "react-icons/io5";
import { FaPhone } from "react-icons/fa";
import { Bars3Icon, ChartPieIcon, XMarkIcon } from "@heroicons/react/24/outline";
import {
  ChevronDownIcon,
  PhoneIcon,
  PlayCircleIcon,
} from "@heroicons/react/20/solid";

import vetConnectLogo from "../assets/svgs/vetConnectLogo.svg";
import { Link, Navigate } from "react-router-dom";
import useSimpleAuth from "../hooks/useSimpleAuth";

const products = [
  {
    name: "Banho",
    description: "Deixe seu pet limpo e cheiroso",
    href: "#",
    icon: FaShower,
  },
  {
    name: "Tosa",
    description: "Estilize o pelo do seu pet",
    href: "#",
    icon: IoCutSharp,
  },
  {
    name: "Castração",
    description: "Castre seu Animal",
    href: "#",
    icon: MdPets,
  },
  {
    name: "Vacinação",
    description: "Vacine seu pet",
    href: "#",
    icon: TbVaccine,
  },
];

const callsToAction = [{ name: "Entre em Contato", href: "#", icon: FaPhone }];

function classNames(...classes: string[]) {
  return classes.filter(Boolean).join(" ");
}

export default function Navbar() {
  const userId = useSelector(getId);
  const auth = useSimpleAuth();
  const [isOpen, setIsOpen] = useState(false);

  useEffect(() => {}, [userId]);

  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);

  return (
    <header className="bg-white">      
      <nav
        className="max-w-8xl mx-auto flex items-center justify-between p-6 lg:px-8 font-inter"
        aria-label="Global"
      >
        <div className="flex lg:flex-1">
          <Link
            to="/"
            className="-m-1.5 p-1.5 flex flex-row justify-center items-center"
          >
            <span className="sr-only">VetConnect</span>
            <img
              className="h-14 w-auto"
              src={vetConnectLogo}
              alt="Logo da Empresa"
            />
            <span className="font-black text-2lg">VetConnect</span>
          </Link>
        </div>
        <div className="flex lg:hidden">
          <button
            type="button"
            className="-m-2.5 inline-flex items-center justify-center rounded-md p-2.5 text-gray-700"
            onClick={() => setMobileMenuOpen(true)}
          >
            <span className="sr-only">Open main menu</span>
            <Bars3Icon className="h-6 w-6" aria-hidden="true" />
          </button>
        </div>
        <Popover.Group className="hidden lg:flex lg:gap-x-12">
          <Link to="/" className="text-sm font-semibold leading-6 text-gray-900">
            Página principal
          </Link>

          <Popover className="relative">
            <Popover.Button className="flex items-center gap-x-1 text-sm font-semibold leading-6 text-gray-900">
              Agendar serviço
              <ChevronDownIcon
                className="h-5 w-5 flex-none text-gray-400"
                aria-hidden="true"
              />
            </Popover.Button>

            <Transition
              as={Fragment}
              enter="transition ease-out duration-200"
              enterFrom="opacity-0 translate-y-1"
              enterTo="opacity-100 translate-y-0"
              leave="transition ease-in duration-150"
              leaveFrom="opacity-100 translate-y-0"
              leaveTo="opacity-0 translate-y-1"
            >
              <Popover.Panel className="absolute -left-8 top-full z-10 mt-3 w-screen max-w-md overflow-hidden rounded-3xl bg-white shadow-lg ring-1 ring-gray-900/5">
                <div onClick={() => setIsOpen(!isOpen)} className="p-4">
                  {products.map((item) => (
                    <div
                      key={item.name}
                      className="group relative flex items-center gap-x-6 rounded-lg p-4 text-sm leading-6 hover:bg-gray-50"
                    >
                      <div className="flex h-11 w-11 flex-none items-center justify-center rounded-lg bg-gray-50 group-hover:bg-white">
                        <item.icon
                          className="h-6 w-6 text-gray-600 group-hover:text-indigo-600"
                          aria-hidden="true"
                        />
                      </div>
                      <div className="flex-auto">
                        <Link
                          to={item.href}
                          className="block font-semibold text-gray-900"
                        >
                          {item.name}
                          <span className="absolute inset-0" />
                        </Link>

                        <p className="mt-1 text-gray-600">{item.description}</p>
                      </div>
                    </div>
                  ))}
                </div>
                <div className="grid  items-center divide-x divide-gray-900/5 bg-gray-50">
                  {callsToAction.map((item) => (
                    <a
                      key={item.name}
                      href={item.href}
                      className="flex text-center items-center justify-center gap-x-2.5 p-3 text-sm font-semibold leading-6 text-gray-900 hover:bg-gray-100"
                    >
                      <item.icon
                        className="h-5 w-5 flex-none text-gray-400"
                        aria-hidden="true"
                      />
                      {item.name}
                    </a>
                  ))}
                </div>
              </Popover.Panel>
            </Transition>
          </Popover>
          <Link
            to="/unidades"
            className="text-sm font-semibold leading-6 text-gray-900"
          >
            Unidades
          </Link>

          {auth ? (
            <Link
              to="/animal"
              className="text-sm font-semibold leading-6 text-gray-900"
            >
              Meus Pets
            </Link>
          ) : (
            ""
          )}
        </Popover.Group>
        <div className="hidden lg:flex lg:flex-1 lg:justify-end">
          <Link to={auth ? "/user/client" : "/signin"}>
            <ButtonPrimary text={userId == -1 ? "Entrar" : "Perfil"} />
          </Link>
        </div>
      </nav>
      <Dialog
        as="div"
        className="lg:hidden"
        open={mobileMenuOpen}
        onClose={setMobileMenuOpen}
      >
        <div className="fixed inset-0 z-10" />
        <Dialog.Panel className="fixed inset-y-0 right-0 z-10 w-full overflow-y-auto bg-white px-6 py-6 sm:max-w-sm sm:ring-1 sm:ring-gray-900/10">
          <div className="flex items-center justify-between">
            <a href="#" className="-m-1.5 p-1.5">
              <span className="sr-only">VetConnect</span>
              <div className="font-black text-lg">Menu</div>
            </a>
            <button
              type="button"
              className="-m-2.5 rounded-md p-2.5 text-gray-700"
              onClick={() => setMobileMenuOpen(false)}
            >
              <span className="sr-only">Close menu</span>
              <XMarkIcon className="h-6 w-6" aria-hidden="true" />
            </button>
          </div>
          <div className="mt-6 flow-root">
            <div className="-my-6 divide-y divide-gray-500/10">
              <div className="space-y-2 py-6">
                <Link
                  to="/"
                  className="text-sm font-semibold leading-6 text-gray-900"
                >
                  Página principal
                </Link>

                <Disclosure
                  as="div"
                  className="-mx-3 text-sm font-semibold leading-6 text-gray-900"
                >
                  {({ open }) => (
                    <>
                      <Disclosure.Button className="flex w-full items-center justify-between rounded-lg py-2 pl-3 pr-3.5 text-sm font-semibold leading-7 hover:bg-gray-50">
                        Agendar serviço
                        <ChevronDownIcon
                          className={classNames(
                            open ? "rotate-180" : "",
                            "h-5 w-5 flex-none"
                          )}
                          aria-hidden="true"
                        />
                      </Disclosure.Button>
                      <Disclosure.Panel className="mt-2 space-y-2">
                        {[...products, ...callsToAction].map((item) => (
                          <Disclosure.Button
                            key={item.name}
                            as="a"
                            href={item.href}
                            className="block rounded-lg py-2 pl-6 pr-3 text-sm font-semibold leading-7 text-gray-900 hover:bg-gray-50"
                          >
                            {item.name}
                          </Disclosure.Button>
                        ))}
                      </Disclosure.Panel>
                    </>
                  )}
                </Disclosure>

                <Link
                  to="/unidades"
                  className="text-sm font-semibold leading-6 text-gray-900"
                >
                  Unidades
                </Link>
              </div>
              <div className="py-6">
                <Link
                  to={auth ? "/user/client" : "/signin"}
                  className="-mx-3 block rounded-lg px-3 py-2.5 text-base font-semibold leading-7 text-gray-900 hover:bg-gray-50"
                >
                  <ButtonPrimary text={auth ? "Perfil" : "Entrar"} width="w-full" />
                </Link>
              </div>
            </div>
          </div>
        </Dialog.Panel>
      </Dialog>
    </header>
  );
}
