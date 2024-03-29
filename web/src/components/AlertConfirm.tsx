import { Fragment, useRef, useState } from "react";
import { Dialog, Transition } from "@headlessui/react";
import { ExclamationTriangleIcon } from "@heroicons/react/24/outline";
import ButtonPrimary from "./buttons/ButtonPrimary";
import ButtonSecundary from "./buttons/ButtonSecundary";

interface AlertModalProps {
  type: "insert" | "edit" | "delete";
  isOpen: boolean;
  message: string;
  onConfirmFunction: () => void;
  setIsOpen: (isOpen: boolean) => void;
}

export default function AlertConfirm({
  type,
  isOpen,
  message,
  onConfirmFunction,
  setIsOpen,
}: AlertModalProps) {
  const handleSubmit = async () => {
    await onConfirmFunction();
    setIsOpen(false);
  };

  return (
    <Transition appear show={isOpen} as={Fragment}>
      <Dialog
        as="div"
        className="absolute z-[11]"
        onClose={() => setIsOpen(!isOpen)}
      >
        <Transition.Child
          as={Fragment}
          enter="ease-out duration-300"
          enterFrom="opacity-0"
          enterTo="opacity-100"
          leave="ease-in duration-200"
          leaveFrom="opacity-100"
          leaveTo="opacity-0"
        >
          <div className="fixed inset-0 bg-black/25" />
        </Transition.Child>

        <div className="fixed inset-0 overflow-y-auto">
          <div className="flex min-h-full items-center justify-center p-4 text-center">
            <Transition.Child
              as={Fragment}
              enter="ease-out duration-300"
              enterFrom="opacity-0 scale-95"
              enterTo="opacity-100 scale-100"
              leave="ease-in duration-200"
              leaveFrom="opacity-100 scale-100"
              leaveTo="opacity-0 scale-95"
            >
              <Dialog.Panel className="w-full max-w-md transform overflow-hidden rounded bg-white p-6 text-left align-middle shadow-xl transition-all">
                <Dialog.Title
                  as="h3"
                  className="text-lg font-medium leading-6 text-gray-900"
                >
                  <span>Confirmação</span>
                </Dialog.Title>
                  <div className="mt-2">
                    <p className="text-md text-gray-800">{message}</p>
                  </div>

                  <div className="mt-4 flex gap-2">
                    <ButtonPrimary text="Sim" onClickFunction={handleSubmit}/>
                    <ButtonSecundary                      
                      text="Não"
                      onClickFunction={() => setIsOpen(!isOpen)}
                    />
                  </div>
                {/* <form action="POST" onSubmit={handleSubmit}>
                </form> */}
              </Dialog.Panel>
            </Transition.Child>
          </div>
        </div>
      </Dialog>
    </Transition>
  );
}
